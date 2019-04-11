package org.folio.edge.sip2.repositories;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.lang.invoke.MethodHandles;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.folio.edge.sip2.domain.messages.enumerations.Messages;
import org.folio.edge.sip2.domain.messages.responses.ACSStatus;

public class ConfigurationRepository {

  private IResourceProvider resourceProvider;
  private final Logger log;

  /**
   * Constructor that takes an IResourceProvider.
   *
   * @param resourceProvider This can be DefaultResourceProvider or any provider in the future.
   */
  public ConfigurationRepository(IResourceProvider resourceProvider) {
    if (resourceProvider == null) {
      throw new IllegalArgumentException("configGateway is null");
    }
    this.resourceProvider = resourceProvider;
    log = LogManager.getLogger(MethodHandles.lookup().lookupClass());
  }

  /**
   * Method that returns ACSStatus built from the ACS configuration JSON snippet.
   *
   * @return ACSStatus object
   */
  public ACSStatus getACSStatus() {
    JsonObject acsConfiguration = retrieveAcsConfiguration();

    ACSStatus acsStatus = null;
    if (acsConfiguration != null) {
      ACSStatus.ACSStatusBuilder builder = ACSStatus.builder();

      builder.checkinOk(acsConfiguration.getBoolean("onlineStatus"));
      builder.acsRenewalPolicy(acsConfiguration.getBoolean("acsRenewalPolicy"));
      builder.checkoutOk(acsConfiguration.getBoolean("checkoutOk"));
      builder.dateTimeSync(ZonedDateTime.now());
      builder.institutionId(acsConfiguration.getString("institutionId"));
      builder.libraryName(acsConfiguration.getString("libraryName"));
      builder.offLineOk(acsConfiguration.getBoolean("checkoutOk"));
      builder.onLineStatus(acsConfiguration.getBoolean("onlineStatus"));
      builder.printLine(acsConfiguration.getString("printLine"));
      builder.protocolVersion(acsConfiguration.getString("protocolVersion"));
      builder.retriesAllowed(acsConfiguration.getInteger("retriesAllowed"));
      builder.screenMessage(acsConfiguration.getString("screenMessage"));
      builder.statusUpdateOk(acsConfiguration.getBoolean("statusUpdateOk"));
      builder.terminalLocation(acsConfiguration.getString("terminalLocation"));
      builder.timeoutPeriod(acsConfiguration.getInteger("timeoutPeriod"));
      builder.supportedMessages(getSupportedMessagesFromJson(
              acsConfiguration.getJsonArray("supportedMessages")));

      acsStatus = builder.build();

    } else {
      log.error("The JsonConfig object is null");
    }

    return acsStatus;
  }


  /**
   * Method that retrieves the tenant configuration with the tenantID as configKey.
   *
   * @param configKey key to retrieving the desired tenant configuration. It is tenantId.
   * @return JSON object containing tenant configuration
   */
  public JsonObject retrieveTenantConfiguration(String configKey) {

    JsonObject configJson = null;

    JsonObject jsonFile = resourceProvider.retrieveResource(null);
    JsonArray tenantConfigurations = jsonFile.getJsonArray("tenantConfigurations");
    Optional tenantConfigObject = tenantConfigurations
        .stream()
        .filter(config -> ((JsonObject)config).getString("tenantId").equalsIgnoreCase(configKey))
        .findFirst();

    if (tenantConfigObject.isPresent()) {
      configJson = (JsonObject) tenantConfigObject.get();
    }

    return configJson;
  }

  private JsonObject retrieveAcsConfiguration() {

    JsonObject acsConfiguration = null;
    JsonObject fullConfiguration = resourceProvider.retrieveResource(null);

    if (fullConfiguration != null) {
      acsConfiguration = fullConfiguration.getJsonObject("acsConfiguration");
    }

    return acsConfiguration;
  }

  private Set<Messages> getSupportedMessagesFromJson(JsonArray supportedMessages) {
    return supportedMessages
        .stream()
        .filter(el -> ((JsonObject) el).getString("isSupported").equalsIgnoreCase("Y"))
        .map(el -> Messages.valueOf(((JsonObject) el).getString("messageName")))
        .collect(Collectors.toSet());
  }
}