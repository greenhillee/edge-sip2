package org.folio.edge.sip2.domain.messages.responses;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.folio.edge.sip2.domain.messages.responses.EndSessionResponse.builder;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

class EndSessionResponseTests {
  final Boolean endSession = TRUE;
  final ZonedDateTime transactionDate = ZonedDateTime.now();
  final String institutionId = "diku";
  final String patronIdentifier = "1234567890";
  final String screenMessage = "Hello, world!";
  final String printLine = "Dot matrix";

  @Test
  void testGetEndSession() {
    final EndSessionResponse esr = builder().endSession(endSession).build();
    assertEquals(endSession, esr.getEndSession());
    assertNull(esr.getTransactionDate());
    assertNull(esr.getInstitutionId());
    assertNull(esr.getPatronIdentifier());
    assertNull(esr.getScreenMessage());
    assertNull(esr.getPrintLine());
  }

  @Test
  void testGetTransactionDate() {
    final EndSessionResponse esr = builder()
        .transactionDate(transactionDate)
        .build();
    assertNull(esr.getEndSession());
    assertEquals(transactionDate, esr.getTransactionDate());
    assertNull(esr.getInstitutionId());
    assertNull(esr.getPatronIdentifier());
    assertNull(esr.getScreenMessage());
    assertNull(esr.getPrintLine());
  }

  @Test
  void testGetInstitutionId() {
    final EndSessionResponse esr = builder()
        .institutionId(institutionId)
        .build();
    assertNull(esr.getEndSession());
    assertNull(esr.getTransactionDate());
    assertEquals(institutionId, esr.getInstitutionId());
    assertNull(esr.getPatronIdentifier());
    assertNull(esr.getScreenMessage());
    assertNull(esr.getPrintLine());
  }

  @Test
  void testGetPatronIdentifier() {
    final EndSessionResponse esr = builder()
        .patronIdentifier(patronIdentifier)
        .build();
    assertNull(esr.getEndSession());
    assertNull(esr.getTransactionDate());
    assertNull(esr.getInstitutionId());
    assertEquals(patronIdentifier, esr.getPatronIdentifier());
    assertNull(esr.getScreenMessage());
    assertNull(esr.getPrintLine());
  }

  @Test
  void testGetScreenMessage() {
    final EndSessionResponse esr = builder()
        .screenMessage(screenMessage)
        .build();
    assertNull(esr.getEndSession());
    assertNull(esr.getTransactionDate());
    assertNull(esr.getInstitutionId());
    assertNull(esr.getPatronIdentifier());
    assertEquals(screenMessage, esr.getScreenMessage());
    assertNull(esr.getPrintLine());
  }

  @Test
  void testGetPrintLine() {
    final EndSessionResponse esr = builder().printLine(printLine).build();
    assertNull(esr.getEndSession());
    assertNull(esr.getTransactionDate());
    assertNull(esr.getInstitutionId());
    assertNull(esr.getPatronIdentifier());
    assertNull(esr.getScreenMessage());
    assertEquals(printLine, esr.getPrintLine());
  }

  @Test
  void testCompleteEndSessionResponse() {
    final EndSessionResponse esr = builder()
        .endSession(endSession)
        .transactionDate(transactionDate)
        .institutionId(institutionId)
        .patronIdentifier(patronIdentifier)
        .screenMessage(screenMessage)
        .printLine(printLine)
        .build();
    assertAll("EndSessionResponse",
        () -> assertEquals(endSession, esr.getEndSession()),
        () -> assertEquals(transactionDate, esr.getTransactionDate()),
        () -> assertEquals(institutionId, esr.getInstitutionId()),
        () -> assertEquals(patronIdentifier, esr.getPatronIdentifier()),
        () -> assertEquals(screenMessage, esr.getScreenMessage()),
        () -> assertEquals(printLine, esr.getPrintLine())
    );
  }

  @Test
  void testEquals() {
    final EndSessionResponse esr1 = builder()
        .endSession(endSession)
        .transactionDate(transactionDate)
        .institutionId(institutionId)
        .patronIdentifier(patronIdentifier)
        .screenMessage(screenMessage)
        .printLine(printLine)
        .build();
    final EndSessionResponse esr2 = builder()
        .endSession(endSession)
        .transactionDate(transactionDate)
        .institutionId(institutionId)
        .patronIdentifier(patronIdentifier)
        .screenMessage(screenMessage)
        .printLine(printLine)
        .build();
    assertTrue(esr1.equals(esr2));
    assertTrue(esr2.equals(esr1));
  }

  @Test
  void testNotEquals() {
    final EndSessionResponse esr1 = builder()
        .endSession(endSession)
        .transactionDate(transactionDate)
        .institutionId(institutionId)
        .patronIdentifier(patronIdentifier)
        .screenMessage(screenMessage)
        .printLine(printLine)
        .build();
    final EndSessionResponse esr2 = builder()
        .endSession(FALSE)
        .transactionDate(ZonedDateTime.now())
        .institutionId("test")
        .patronIdentifier("0987654321")
        .screenMessage("Welcome to the jungle.")
        .printLine("Print print print")
        .build();
    assertFalse(esr1.equals(esr2));
    assertFalse(esr2.equals(esr1));
  }

  @Test
  void testToString() {
    final String expectedString = new StringBuilder()
        .append("EndSessionResponse [endSession=").append(endSession)
        .append(", transactionDate=").append(transactionDate)
        .append(", institutionId=").append(institutionId)
        .append(", patronIdentifier=").append(patronIdentifier)
        .append(", screenMessage=").append(screenMessage)
        .append(", printLine=").append(printLine)
        .append(']').toString();
    final EndSessionResponse esr = builder()
        .endSession(endSession)
        .transactionDate(transactionDate)
        .institutionId(institutionId)
        .patronIdentifier(patronIdentifier)
        .screenMessage(screenMessage)
        .printLine(printLine)
        .build();
    assertEquals(expectedString, esr.toString());
  }
}
