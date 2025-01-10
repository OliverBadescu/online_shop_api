package mycode.online_shop_api.global_exceptions;

import lombok.Builder;

@Builder
public record ErrorMessageDto(String message) {
}
