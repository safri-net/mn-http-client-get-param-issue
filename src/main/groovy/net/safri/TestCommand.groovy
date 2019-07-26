package net.safri

import io.micronaut.validation.Validated

import javax.annotation.Nullable

@Validated
class TestCommand {
    @Nullable
    String name

    @Nullable
    Type type
}
