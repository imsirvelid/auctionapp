package org.atlantbh.internship.auctionapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info =@Info(
                title = "AuctionAPP API",
                version = "1.0",
                contact = @Contact(
                        name = "Velid Imširović", email = "imsirvelid@gmail.com", url = "https://github.com/imsirvelid/auctionapp"
                ),
                license = @License(
                        name = "GPL 3.0", url = "https://www.gnu.org/licenses/gpl-3.0.html"
                ),
                description = "This swagger contains routes available for AuctionAPP backend"
        ),
        security = {
                @SecurityRequirement(name = "Bearer Authentication")
        }
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER
)
public class OpenAPISecurity {

}
