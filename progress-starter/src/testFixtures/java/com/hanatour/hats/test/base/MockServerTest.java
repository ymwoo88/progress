package com.hanatour.hats.test.base;

import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.net.InetSocketAddress;

public abstract class MockServerTest {

    protected static MockWebServer server;

    @BeforeAll
    protected static void beforeAll() throws IOException {
        server = new MockWebServer();
        server.start();
    }

    @AfterAll
    protected static void afterAll() throws IOException {
        server.shutdown();
    }

    protected static String getHostAndPort() {
        return String.format("http://%s:%d", server.getHostName(), server.getPort());
    }

    protected static InetSocketAddress getIntSocketAddress() {
        return InetSocketAddress.createUnresolved(server.getHostName(), server.getPort());
    }

}
