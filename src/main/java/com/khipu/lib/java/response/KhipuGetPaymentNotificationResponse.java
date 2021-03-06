/* Copyright (c) 2013, khipu SpA
 * All rights reserved.
 * Released under BSD LICENSE, please refer to LICENSE.txt
 */

package com.khipu.lib.java.response;

/**
 * Objeto con la respuesta la información de una notificación instantanea.
 *
 * @author Alejandro Vera (alejandro.vera@khipu.com)
 * @version 1.3
 * @since 2013-12-02
 */
public class KhipuGetPaymentNotificationResponse implements KhipuResponse {

	String _notificationToken;
	String _receiverId;
	String _subject;
	String _amount;
	String _custom;
	String _transactionId;
	String _paymentId;
	String _currency;
	String _payerEmail;
	String _payerIdentifier;
	String _accountNumber;
	String _bankName;

	/**
	 * Establece la información del pago.
	 *
	 * @param notificationToken token de identificación de la notificación
	 * @param receiverId        id de la cuenta de cobro
	 * @param subject           asunto del cobro
	 * @param amount            monto del cobro
	 * @param custom            información adicional del cobro
	 * @param transactionId     código de identificación propio del comercio
	 * @param paymentId         identificador único del pago
	 * @param currency          código de la moneda
	 * @param payerEmail        email del pagador
	 * @param payerIdentifier   identificador del pagador
	 * @param accountNumber     número de cuenta bancaria
	 * @param bankName			el nombre del banco
	 * @since 2015-02-02
	 */
	public KhipuGetPaymentNotificationResponse(String notificationToken,
											   String receiverId,
											   String subject,
											   String amount,
											   String custom,
											   String transactionId,
											   String paymentId,
											   String currency,
											   String payerEmail,
											   String payerIdentifier,
											   String accountNumber,
											   String bankName) {
		_notificationToken = notificationToken;
		_receiverId = receiverId;
		_subject = subject;
		_amount = amount;
		_custom = custom;
		_transactionId = transactionId;
		_paymentId = paymentId;
		_currency = currency;
		_payerEmail = payerEmail;
		_payerIdentifier = payerIdentifier;
		_accountNumber = accountNumber;
		_bankName = bankName;
	}

	/**
	 * Obtiene el token de identificación de la notificación.
	 *
	 * @return el token de identificación.
	 * @since 2015-02-02
	 */
	public String getNotificationToken() {
		return _notificationToken;
	}

	/**
	 * Obtiene el id de la cuenta de cobro.
	 *
	 * @return el id.
	 * @since 2015-02-02
	 */
	public String getReceiverId() {
		return _receiverId;
	}

	/**
	 * Obtiene el asunto del cobro.
	 *
	 * @return el asunto.
	 * @since 2015-02-02
	 */
	public String getSubject() {
		return _subject;
	}

	/**
	 * Obtiene el monto del cobro.
	 *
	 * @return el monto.
	 * @since 2015-02-02
	 */
	public String getAmount() {
		return _amount;
	}

	/**
	 * Obtiene la información adicional del cobro.
	 *
	 * @return la información adicional.
	 * @since 2015-02-02
	 */
	public String getCustom() {
		return _custom;
	}

	/**
	 * Obtiene el código de identificación propio del comercio.
	 *
	 * @return el código de identificación.
	 * @since 2015-02-02
	 */
	public String getTransactionId() {
		return _transactionId;
	}

	/**
	 * Obtiene el identificador único del cobro.
	 *
	 * @return el identificador.
	 * @since 2015-02-02
	 */
	public String getPaymentId() {
		return _paymentId;
	}

	/**
	 * Obtiene el código de la moneda del cobro.
	 *
	 * @return el código de la moneda.
	 * @since 2015-02-02
	 */
	public String getCurrency() {
		return _currency;
	}

	/**
	 * Obtiene el e-mail del pagador.
	 *
	 * @return el e-mail.
	 * @since 2015-02-02
	 */
	public String getPayerEmail() {
		return _payerEmail;
	}

	/**
	 * Obtiene el identificador del pagador.
	 *
	 * @return el identificador.
	 * @since 2015-04-24
	 */
	public String getPayerIdentifier() {
		return _payerIdentifier;
	}

	/**
	 * Obtiene el número de cuenta del pagador.
	 *
	 * @return el número de cuenta.
	 * @since 2015-04-24
	 */
	public String getAccountNumber() { return _accountNumber; }

	/**
	 * Obtiene el banco del pagador.
	 *
	 * @return el nombre de banco.
	 * @since 2015-04-24
	 */
	public String getBankName() { return _bankName;	}

	@Override

	public String toString() {
		return new StringBuilder("notificationToken: ").append(_notificationToken)
				.append(" receiverId: ").append(_receiverId)
				.append(" subject: ").append(_subject)
				.append(" amount: ").append(_amount)
				.append(" custom: ").append(_custom)
				.append(" transactionId: ").append(_transactionId)
				.append(" paymentId: ").append(_paymentId)
				.append(" currency: ").append(_currency)
				.append(" payerEmail: ").append(_payerEmail)
				.append(" payerIdentifier: ").append(_payerIdentifier)
				.append(" accountNumber: ").append(_accountNumber)
				.append(" bankName: ").append(_bankName)
				.toString();
	}
}
