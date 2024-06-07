package team.youngnrich.kakaologindemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.youngnrich.kakaologindemo.dto.KakaoInfoRequestDto;
import team.youngnrich.kakaologindemo.dto.KakaoInfoResponseDto;
import team.youngnrich.kakaologindemo.dto.KakaoTokenRequstDto;
import team.youngnrich.kakaologindemo.dto.KakaoTokenResponseDto;
import team.youngnrich.kakaologindemo.service.KakaoLoginService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kakao")
public class KakaoLoginController {

    private final KakaoLoginService kakaoLoginService;

    // 클라이언트 측에서 Authorization code를 전달해주면 그것으로 카카오측 Access Token 받아오기
    public KakaoTokenResponseDto authorize (KakaoTokenRequstDto requestDto) {
        return kakaoLoginService.authorize(requestDto);
    }

    // 액세스 토큰으로 사용자 정보 가져오기
    public KakaoInfoResponseDto getInfo (KakaoInfoRequestDto requestDto) {
        return kakaoLoginService.getInfo(requestDto);
    }
}
