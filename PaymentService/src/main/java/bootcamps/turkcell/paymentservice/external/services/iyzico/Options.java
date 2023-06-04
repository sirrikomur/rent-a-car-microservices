package bootcamps.turkcell.paymentservice.external.services.iyzico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Options {
    private String apiKey;
    private String secretKey;
    private String baseUrl;
    private String proxyHost;
    private int proxyPort;
}
