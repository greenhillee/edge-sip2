package org.folio.edge.sip2.handlers.freemarker;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import freemarker.template.Template;
import org.folio.edge.sip2.parser.Command;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FreemarkerRepositoryTests {

  private static FreemarkerRepository freemarkerRepoInstance;

  @BeforeAll
  public static void initRepo() {
    freemarkerRepoInstance = FreemarkerRepository.getInstance();
  }

  @Test
  public void canInitializeRepo() {
    assertNotNull(freemarkerRepoInstance);
  }

  @Test
  public void canGetAcsStatusTemplate() {
    Template template = freemarkerRepoInstance.getFreemarkerTemplate(Command.ACS_STATUS);
    assertNotNull(template);
  }

  @Test
  public void canGetNullWhenRequestingInvalidTemplate() {
    Template template = freemarkerRepoInstance.getFreemarkerTemplate(Command.CHECKOUT);
    assertNull(template);
  }
}
