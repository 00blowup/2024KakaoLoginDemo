package team.youngnrich.kakaologindemo.controller;

import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.youngnrich.kakaologindemo.dto.KakaoInfoRequestDto;
import team.youngnrich.kakaologindemo.dto.KakaoLoginRequestDto;
import team.youngnrich.kakaologindemo.dto.KakaoTokenRequestDto;
import team.youngnrich.kakaologindemo.service.KakaoLoginService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kakao")
public class KakaoLoginController {

    private final KakaoLoginService kakaoLoginService;

    // 클라이언트 측에서 Authorization code를 전달해주면 그것으로 카카오측 Access Token 받아오기
    @PostMapping("/auth")
    public String authorize (@RequestBody KakaoTokenRequestDto requestDto) {
        return kakaoLoginService.authorize(requestDto);
    }

    // 액세스 토큰으로 사용자 정보 가져오기
    @PostMapping("/info")
    public String getInfo (@RequestBody KakaoInfoRequestDto requestDto) {
        return kakaoLoginService.getInfo(requestDto);
    }

    // 실제 사용할 프로세스(카카오유저 id로 사용자를 구분하기 위해, 위의 authorize와 getInfo를 모두 수행한 후 id만 꺼내오기)
    @PostMapping("/getId")
    public ResponseEntity<String> login (@RequestBody KakaoLoginRequestDto requestDto) throws ParseException {
        return ResponseEntity.ok().body(kakaoLoginService.login(requestDto));
    }
}
