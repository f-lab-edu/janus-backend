package kr.ai.janus.auth.oauth;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class OAuthProviderConverter implements Converter<String, OAuthProvider> {

    @Override
    public OAuthProvider convert(String source) {
        try {
            return OAuthProvider.valueOf(source.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new UnsupportedProviderException(source, e);
        }
    }
}
