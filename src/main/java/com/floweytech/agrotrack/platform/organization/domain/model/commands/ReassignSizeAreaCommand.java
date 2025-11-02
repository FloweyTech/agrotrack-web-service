package com.floweytech.agrotrack.platform.organization.domain.model.commands;

import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.SizeArea;

public record ReassignSizeAreaCommand(
    Long plotId,
    SizeArea sizeArea
) {
}

