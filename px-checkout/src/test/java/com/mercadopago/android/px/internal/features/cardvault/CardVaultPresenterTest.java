package com.mercadopago.android.px.internal.features.cardvault;

import android.content.Intent;
import com.mercadopago.android.px.addons.ESCManagerBehaviour;
import com.mercadopago.android.px.internal.repository.AmountConfigurationRepository;
import com.mercadopago.android.px.internal.repository.CardTokenRepository;
import com.mercadopago.android.px.internal.repository.PaymentSettingRepository;
import com.mercadopago.android.px.internal.repository.UserSelectionRepository;
import com.mercadopago.android.px.internal.util.TextUtil;
import com.mercadopago.android.px.model.AmountConfiguration;
import com.mercadopago.android.px.model.Card;
import com.mercadopago.android.px.model.CardInfo;
import com.mercadopago.android.px.model.Issuer;
import com.mercadopago.android.px.model.PayerCost;
import com.mercadopago.android.px.model.PaymentRecovery;
import com.mercadopago.android.px.model.SavedESCCardToken;
import com.mercadopago.android.px.model.Token;
import com.mercadopago.android.px.tracking.internal.model.Reason;
import com.mercadopago.android.px.utils.StubSuccessMpCall;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CardVaultPresenterTest {

    private CardVaultPresenter presenter;

    @Mock private PaymentSettingRepository paymentSettingRepository;
    @Mock private UserSelectionRepository userSelectionRepository;
    @Mock private AmountConfigurationRepository amountConfigurationRepository;
    @Mock private CardTokenRepository cardTokenRepository;
    @Mock private ESCManagerBehaviour escManagerBehaviour;
    @Mock private AmountConfiguration amountConfiguration;
    @Mock private CardVault.View view;

    @Before
    public void setUp() {
        when(amountConfigurationRepository.getCurrentConfiguration()).thenReturn(amountConfiguration);

        presenter = new CardVaultPresenter(userSelectionRepository, paymentSettingRepository, escManagerBehaviour,
            amountConfigurationRepository, cardTokenRepository);

        presenter.setPaymentRecovery(null);
        presenter.attachView(view);
    }

    private void configureMockedCard() {
        final Card card = mock(Card.class);
        when(card.getId()).thenReturn("1");
        when(card.getFirstSixDigits()).thenReturn("123456");
        when(card.getLastFourDigits()).thenReturn("1234");
        when(userSelectionRepository.getCard()).thenReturn(card);
        presenter.setCard(card);
    }

    @Test
    public void whenTokenIsRecoverableThenStartTokenRecoveryFlow() {
        final Token token = mock(Token.class);
        final PaymentRecovery paymentRecovery = mock(PaymentRecovery.class);
        when(paymentRecovery.isTokenRecoverable()).thenReturn(true);
        when(paymentRecovery.getToken()).thenReturn(token);

        presenter.setPaymentRecovery(paymentRecovery);
        presenter.initialize();

        verify(view).askForSecurityCodeFromTokenRecovery(Reason.from(paymentRecovery));
        verifyNoMoreInteractions(view);
    }

    @Test
    public void whenSavedCardNotPresentThenStartGuessingFlow() {
        presenter.initialize();

        verify(view).askForCardInformation();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void whenFlowCanceledThenFinishFlow() {
        presenter.onResultCancel();

        verify(view).cancelCardVault();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void whenCardHasInstallmentSelectedAndTokenThenFinishFlow() {
        presenter.resolveSecurityCodeRequest();

        verify(view).finishWithResult();
        verifyNoMoreInteractions(view);
    }

    /**
     * Guessing
     */

    @Test
    public void whenGuessingCardHasInstallmentSelectedAndWithoutTokenThenStartSecurityCodeFlow() {
        configureMockedCard();
        final Card card = userSelectionRepository.getCard();
        when(escManagerBehaviour.getESC(card.getId(), card.getFirstSixDigits(), card.getLastFourDigits()))
            .thenReturn(TextUtil.EMPTY);

        presenter.resolveInstallmentsRequest();

        verify(view).startSecurityCodeActivity(Reason.SAVED_CARD);
    }

    @Test
    public void whenGuessingHasIssuerAndPayerCostThenFinishWithResult() {
        when(userSelectionRepository.getIssuer()).thenReturn(mock(Issuer.class));
        when(userSelectionRepository.getPayerCost()).thenReturn(mock(PayerCost.class));
        when(paymentSettingRepository.getToken()).thenReturn(mock(Token.class));

        presenter.resolveNewCardRequest(mock(Intent.class));

        verify(view).finishWithResult();
    }

    @Test
    public void whenGuessingWithIssuerSelectedAndPayerCostNotSelectedThenStartInstallmentFlow() {
        when(userSelectionRepository.getIssuer()).thenReturn(mock(Issuer.class));
        when(paymentSettingRepository.getToken()).thenReturn(mock(Token.class));

        presenter.resolveNewCardRequest(mock(Intent.class));

        verify(view).askForInstallments(any(CardInfo.class));
    }

    @Test
    public void whenGuessingCardHasNoIssuerThenStartIssuerFlow() {
        when(paymentSettingRepository.getToken()).thenReturn(mock(Token.class));

        presenter.resolveNewCardRequest(mock(Intent.class));

        verify(view).startIssuersActivity(argThat(List::isEmpty));
    }

    /**
     * Saved card
     */

    @Test
    public void verifyResolvesEmptyPayerCostList() {
        configureMockedCard();

        presenter.initialize();

        verify(view).showEmptyPayerCostScreen();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void verifyResolvesOnSelectedPayerCostPayerCostListWithoutESC() {
        configureMockedCard();
        final Card card = userSelectionRepository.getCard();
        when(escManagerBehaviour.getESC(card.getId(), card.getFirstSixDigits(), card.getLastFourDigits()))
            .thenReturn(TextUtil.EMPTY);
        when(userSelectionRepository.getPayerCost()).thenReturn(mock(PayerCost.class));

        presenter.initialize();

        verify(view).startSecurityCodeActivity(Reason.SAVED_CARD);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void verifyResolvesOnSelectedPayerCostPayerCostListWithESC() {
        configureMockedCard();
        final Card card = userSelectionRepository.getCard();
        final SavedESCCardToken savedESCCardToken = any(SavedESCCardToken.class);

        when(cardTokenRepository.createToken(savedESCCardToken))
            .thenReturn(new StubSuccessMpCall<>(mock(Token.class)));
        when(escManagerBehaviour.getESC(card.getId(), card.getFirstSixDigits(), card.getLastFourDigits()))
            .thenReturn("1");
        when(userSelectionRepository.getPayerCost()).thenReturn(mock(PayerCost.class));

        presenter.initialize();

        verify(view).showProgressLayout();
        verify(view).finishWithResult();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void whenOnlyOnePayerCostThenSelectsAndAsksForSecCode() {
        configureMockedCard();
        final Card card = userSelectionRepository.getCard();
        when(escManagerBehaviour.getESC(card.getId(), card.getFirstSixDigits(), card.getLastFourDigits()))
            .thenReturn(TextUtil.EMPTY);
        final List<PayerCost> payerCosts = Collections.singletonList(mock(PayerCost.class));
        when(amountConfiguration.getPayerCosts()).thenReturn(payerCosts);

        presenter.initialize();

        verify(view).startSecurityCodeActivity(Reason.SAVED_CARD);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void verifyResolvesDisplayInstallments() {
        configureMockedCard();
        final List<PayerCost> payerCosts = Arrays.asList(mock(PayerCost.class), mock(PayerCost.class));
        when(amountConfiguration.getPayerCosts()).thenReturn(payerCosts);

        presenter.initialize();

        verify(view).askForInstallments(any(CardInfo.class));
        verifyNoMoreInteractions(view);
    }

    @Test
    public void whenIsOneTapFlowThenDontAskForInstallmentsAndContinueFlow() {
        configureMockedCard();
        when(userSelectionRepository.getPayerCost()).thenReturn(mock(PayerCost.class));

        presenter.initialize();

        verifyNoMoreInteractions(amountConfigurationRepository);
    }
}