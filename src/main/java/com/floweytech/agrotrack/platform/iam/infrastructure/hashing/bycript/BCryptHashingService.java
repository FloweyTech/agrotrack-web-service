package com.floweytech.agrotrack.platform.iam.infrastructure.hashing.bycript;

import com.floweytech.agrotrack.platform.iam.application.internal.outboundedservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This interface is a marker interface for the BCrypt hashing service.
 * It extends the {@link HashingService} and {@link PasswordEncoder} interfaces..
 */
public interface BCryptHashingService extends HashingService, PasswordEncoder {
}

