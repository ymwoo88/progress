package com.hanatour.hats.test.base;

import org.junit.jupiter.api.DisplayNameGeneration;

import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

@TestProfile
@DisplayNameGeneration(ReplaceUnderscores.class)
public interface DefaultTest {
}
