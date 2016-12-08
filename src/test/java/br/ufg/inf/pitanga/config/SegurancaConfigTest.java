package br.ufg.inf.pitanga.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SegurancaConfigTest {

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockHttpServletRequest request;

    private MockHttpServletResponse response;

    private MockFilterChain chain;

    @Before
    public void setUp() throws Exception {
        this.request = new MockHttpServletRequest();
        this.response = new MockHttpServletResponse();
        this.chain = new MockFilterChain();
    }

    @Test
    public void testeNegaRequestInvalida() throws Exception {
        this.springSecurityFilterChain.doFilter(this.request, this.response, this.chain);
        assertThat(this.response.getStatus())
            .isEqualTo(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Test
    public void userAuthenticates() throws Exception {
        this.request.addHeader("Authorization",
            "Basic " + new String(Base64.encode("user:password".getBytes("UTF-8"))));

        this.springSecurityFilterChain.doFilter(this.request, this.response, this.chain);

        assertThat(this.response.getStatus()).isEqualTo(HttpServletResponse.SC_OK);
    }
}
