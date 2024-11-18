package com.hanatour.hats.test.plugins.autoparams;

import autoparams.customization.CompositeCustomizer;
import autoparams.customization.SettablePropertyWriter;
import autoparams.lombok.BuilderCustomizer;

public class DomainCustomization extends CompositeCustomizer {

    public DomainCustomization() {
        /**
         * 뒤에서 부터 먼저 시작되기 때문에 EntityBuilderCustomizer가 BuilderCustomizer보다 먼저 실행되야 된다.
         */
        super(
                new SettablePropertyWriter(),
                new BuilderCustomizer(),
                new EntityBuilderCustomizer()
        );
    }
}
