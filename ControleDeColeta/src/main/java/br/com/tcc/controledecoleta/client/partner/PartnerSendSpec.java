package br.com.tcc.controledecoleta.client.partner;

import br.com.tcc.controledecoleta.client.partner.fakepartner.FakePartnerClient;

import java.util.Arrays;
import java.util.Optional;

public enum PartnerSendSpec {
    FAKE_PARTNER_1("Partner1", new FakePartnerClient());

    private final String partnerName;
    private PartnerSendIntegration partnerSendIntegration;
    PartnerSendSpec(String partnerName, PartnerSendIntegration partnerSendIntegration) {
        this.partnerSendIntegration = partnerSendIntegration;
        this.partnerName = partnerName;
    }

    public static Optional<PartnerSendSpec> getPartner(String partnerName) {
        return Arrays.stream(PartnerSendSpec.values()).filter(spec -> spec.getPartnerName().equals(partnerName))
                .findFirst();
    }

    public PartnerSendIntegration getPartnerSendIntegration() {
        return partnerSendIntegration;
    }

    public String getPartnerName(){
        return partnerName;
    }
}
