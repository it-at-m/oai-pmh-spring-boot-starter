package de.muenchen.oss.oai.pmh.starter.webservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class OaiPmhControllerTest {

    @Test
    void getClientIp() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getHeader("X-Forwarded-For")).thenReturn("192.168.1.100");
        String clientIp = OaiPmhController.getClientIp(request);
        assertThat(clientIp).isEqualTo("192.168.1.100");

        Mockito.when(request.getHeader("X-Forwarded-For")).thenReturn("192.168.2.100, 203.0.113.1");
        clientIp = OaiPmhController.getClientIp(request);
        assertThat(clientIp).isEqualTo("192.168.2.100");

        Mockito.when(request.getHeader("X-Forwarded-For")).thenReturn("");
        Mockito.when(request.getRemoteAddr()).thenReturn("192.168.3.100");
        clientIp = OaiPmhController.getClientIp(request);
        assertThat(clientIp).isEqualTo("192.168.3.100");

        Mockito.when(request.getHeader("X-Forwarded-For")).thenReturn(null);
        Mockito.when(request.getRemoteAddr()).thenReturn(null);
        clientIp = OaiPmhController.getClientIp(request);
        assertThat(clientIp).isEqualTo("unknown");
    }
}