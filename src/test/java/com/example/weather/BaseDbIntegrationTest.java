package com.example.weather;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("tst")
public abstract class BaseDbIntegrationTest {}
