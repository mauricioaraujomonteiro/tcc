package br.com.tcc.controledecoleta.client.partner.fakepartner;

import br.com.tcc.controledecoleta.client.partner.PartnerReadIntegration;
import br.com.tcc.controledecoleta.client.partner.PartnerResponseCommon;
import br.com.tcc.controledecoleta.client.partner.PartnerSendIntegration;
import br.com.tcc.controledecoleta.entity.CollectOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FakePartnerClient implements PartnerReadIntegration<FakePartnerResponse>, PartnerSendIntegration {

    Logger LOG = LoggerFactory.getLogger(FakePartnerClient.class);
    @Override
    public FakePartnerResponse read() {
        return null;
    }

    @Override
    public PartnerResponseCommon send(CollectOrder order) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddhhmmss");
        final String format = sdf.format(new Date());
        final PartnerResponseCommon response = new PartnerResponseCommon();
        response.setOrderId(String.format("P1%s", format));
        LOG.info("Sendind request to the partner: {}", order);
        return response;
    }
}
