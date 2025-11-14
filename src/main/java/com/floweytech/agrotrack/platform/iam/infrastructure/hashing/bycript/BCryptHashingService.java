package com.floweytech.agrotrack.platform.iam.infrastructure.hashing.bycript;

import com.floweytech.agrotrack.platform.iam.application.internal.outboundedservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}

