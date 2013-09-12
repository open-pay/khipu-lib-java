khipu
=====

[khipu - Recarga, paga, cobra](https://khipu.com)

Biblioteca JAVA para utilizar los servicios de Khipu.com

Versión Biblioteca: 1.0

Versión API Khipu: 1.1 
Versión API de notificación: 1.2

La documentación de Khipu.com se puede ver desde aquí:
https://khipu.com/page/api

Uso
---

Si usas Maven en tu proyecto puedes agregar la siguiente dependencia en tu archivo pom.xml

```XML
<dependency>
    <groupId>com.khipu</groupId>
    <artifactId>lib-khipu</artifactId>
    <version>1.1.1</version>
</dependency>
```

Si usas Ivy, Grape, Grails, Buildr, Scala SBT o quieres directamente bajar los .jar puedes buscar khipu en <a href="http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22lib-khipu%22">Maven Central</a>.


Introducción
------------

Esta biblioteca implementa los siguientes servicios de khipu:


1) Crear cobros y enviarlos por mail.
2) Crear una página de Pago.
3) Recibir y validar la notificación de un pago.
4) Verificar el estado de una cuenta de cobro.
5) Verificar el estado de un pago.
6) Marcar un pago como pagado.
7) Marcar un cobro como expirado.
8) Marcar un pago como rechazado.


1) Crear cobros y enviarlos por mail.
------------------------------------

Este servicio entrega un objeto KhipuEmailResponse que contiene el identificador
del cobro generado así como una lista de los pagos asociados a este cobro. Por cada 
pago se tiene el ID, el correo asociado y la URL en khipu para pagar.

```Java
KhipuCreateEmail createEmail = Khipu.getCreateEmail(ID_DEL_COBRADOR, SECRET_DEL_COBRADOR);
createEmail.setSubject("Un cobro desde java");
createEmail.setExpiresDate(new Date(System.currentTimeMillis() + (30 * 24 * 60 * 60 * 1000)));
createEmail.addDestinatary("John Doe", "john.doe@gmail.com", 10);
createEmail.addDestinatary("Jane Dow", "jane.doe@gmail.com", 100);
createEmail.setSendEmails(true);
createEmail.setBody("Un cuerpo para este cobro");
try {
	KhipuEmailResponse response = createEmail.execute();
	System.out.println(response);
} catch (KhipuException e) {
	for (String error : e.getErrors()) {
		System.out.println(error);
	}
} catch (IOException e) {
	e.printStackTrace();
}
```



2) Crear una página de Pago.
-----------------------

Este ejemplo genera un archivo .html con un formulario de pago en khipu.

```Java
PrintWriter out;
try {
	out = new PrintWriter("form.html");
	out.print(Khipu.getPaymentButton(ID_DEL_COBRADOR
		, SECRET_DEL_COBRADOR
		, "john.doe@gmail.com"
		, "Prueba de cobro"
		, "descripción de la prueba"
		, 10
		, "https://ejemplo.com/notify"
		, "https://ejemplo.com/return"
		, "https://ejemplo.com/cancel"
		, "https://ejemplo.com/picture"
		, "datos personalizados"
		, "EEE87"
		, Khipu.BUTTON_100x50));
	out.close();
} catch (FileNotFoundException e) {
	e.printStackTrace();
}
```

3) Validar la notificación de un pago.
----------------------------------------------------

Este ejemplo contacta a khipu para validar los datos de una transacción. Para usar
este servicio no es necesario configurar el SECRET del cobrador. Se retorna un
KhipuVerifyPaymentNotificationResponse cuyo método isVerified devuelve la validez de la
información. En este ejemplo los parámetros se configuran a mano, pero en producción los
datos deben obtenerse desde el HttpRequest.

Se debe notar que ID_DEL_COBRADOR_DESDE_EL_FORMULARIO es el receiver_id que envía khipu. 

```Java
KhipuVerifyPaymentNotification verifyPaymentNotification = Khipu.getVerifyPaymentNotification(ID_DEL_COBRADOR);
verifyPaymentNotification.setPostReceiverId(ID_DEL_COBRADOR_DESDE_EL_FORMULARIO);
verifyPaymentNotification.setApiVersion("1.2");
verifyPaymentNotification.setNotificationId("aq1td2jl2uen");
verifyPaymentNotification.setSubject("Motivo de prueba");
verifyPaymentNotification.setAmount("12575");
verifyPaymentNotification.setCurrency("CLP");
verifyPaymentNotification.setTransaction_id("FTEEE5SWWO");
verifyPaymentNotification.setPayerEmail("john.doe@gmail.com");
verifyPaymentNotification.setCustom("Custom info");
verifyPaymentNotification.setNotificationSignature("j8kPBHaPNy3PkCh...hhLvQbenpGjA==");
try {
	KhipuVerifyPaymentNotificationResponse response = verifyPaymentNotification.execute();
	System.out.println(response.isVerified());
} catch (KhipuException e) {
	for (String error : e.getErrors()) {
		System.out.println(error);
	}
} catch (IOException e) {
	e.printStackTrace();
}
```


4) Verificar el estado de una cuenta de cobro.
---------------------------------------

Este servicio permite consultar el estado de una cuenta khipu. Se devuelve un 
KhipuReceiverStatusResponse que indica si esta cuenta está habilitada para cobrar
y el tipo de cuenta (desarrollo o producción).

```Java
KhipuReceiverStatus receiverStatus = Khipu.getReceiverStatus(ID_DEL_COBRADOR, SECRET_DEL_COBRADOR);
try {
	KhipuReceiverStatusResponse response = receiverStatus.execute();
	System.out.println(response);
} catch (KhipuException e) {
	for (String error : e.getErrors()) {
		System.out.println(error);
	}
} catch (IOException e) {
	e.printStackTrace();
}
```

5) Verificar el estado de un pago.
-------------------------------

Este servició sirve para verificar el estado de un pago.

```Java
KhipuPaymentStatus paymentStatus = Khipu.getPaymentStatus(ID_DEL_COBRADOR, SECRET_DEL_COBRADOR);
paymentStatus.setPaymentId("9fnsggqqi8ho");
try {
	KhipuPaymentStatusResponse response = paymentStatus.execute();
	System.out.println(response);
} catch (KhipuException e) {
	for (String error : e.getErrors()) {
		System.out.println(error);
	}
} catch (IOException e) {
	e.printStackTrace();
} 
```

6) Marcar un cobro como pagado.
-------------------------------

Este servicio permite marcar un cobro como pagado. Si el pagador
paga por un método alternativo a khipu, el cobrador puede marcar 
este cobro como saldado.

```Java
KhipuSetPayedByReceiver setPayedByReceiver = Khipu.getSetPayedByReceiver(ID_DEL_COBRADOR, SECRET_DEL_COBRADOR);
setPayedByReceiver.setPaymentId("54dhfsch6avd");
try {
	KhipuSetPayedByReceiverResponse response = setPayedByReceiver.execute();
	System.out.println(response);
} catch (KhipuException e) {
	for (String error : e.getErrors()) {
		System.out.println(error);
	}
} catch (IOException e) {
	e.printStackTrace();
}
```

7) Marcar un cobro como expirado.
-------------------------------

Este servicio permite adelantar la expiración del cobro. Se puede adjuntar un texto
que será desplegado a la gente que trate de ir a pagar. 

```Java
KhipuSetBillExpired setBillExpired = Khipu.getSetBillExpired(ID_DEL_COBRADOR, SECRET_DEL_COBRADOR);
setBillExpired.setBillId("udmEe");
setBillExpired.setText("Plazo vencido");
try {
	KhipuSetBillExpiredResponse response = setBillExpired.execute();
	System.out.println(response);
} catch (KhipuException e) {
	for (String error : e.getErrors()) {
		System.out.println(error);
	}
} catch (IOException e) {
	e.printStackTrace();
}
```


8) Marcar un cobro como rechazado.
----------------------------------

Este servicio permite rechazar pago con el fin de inhabilitarlo. Permite indicar que el 
pagador rechaza saldar este pago:

```Java
KhipuSetRejectedByPayer setRejectedByPayer = Khipu.getSetRejectedByPayer(ID_DEL_COBRADOR, SECRET_DEL_COBRADOR);
setRejectedByPayer.setPaymentId("0pk7xfgocry4");
setRejectedByPayer.setText("El pago no corresponde");
try {
	KhipuSetRejectedByPayerResponse response = setRejectedByPayer.execute();
	System.out.println(response);
} catch (KhipuException e) {
	for (String error : e.getErrors()) {
		System.out.println(error);
	}
} catch (IOException e) {
	e.printStackTrace();
}
```

