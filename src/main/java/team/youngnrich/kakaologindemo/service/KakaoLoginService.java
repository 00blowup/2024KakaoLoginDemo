package team.youngnrich.kakaologindemo.service;

import lombok.RequiredArgsConstructor;

import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import team.youngnrich.kakaologindemo.dto.KakaoInfoRequestDto;
import team.youngnrich.kakaologindemo.dto.KakaoInfoResponseDto;
import team.youngnrich.kakaologindemo.dto.KakaoTokenRequestDto;
import team.youngnrich.kakaologindemo.dto.KakaoTokenResponseDto;

@Service
@RequiredArgsConstructor
public class KakaoLoginService {
    private RestTemplate restTemplate = new RestTemplate();

    // authorize에 필요한 정보
    private String authorizeUrl = "https://kauth.kakao.com/oauth/token";
    @Value("${kakao.api-key}")
    private String apiKey;
    private String redirectUri = "http://localhost:8080/kakao/callback";

    // getInfo에 필요한 정보
    private String getInfoUrl = "https://kapi.kakao.com/v2/user/me";

    public String authorize(KakaoTokenRequestDto requestDto) {
        String code = requestDto.getCode();
        System.out.println("인가코드: " + code);
        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // 요청 내용 작성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", apiKey);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        // 요청 생성
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        // 카카오 서버의 응답을 리턴
        String response = restTemplate.postForObject(authorizeUrl, request, String.class);
        System.out.println("응답: " + response);
        return response;
    }

    public String getInfo(KakaoInfoRequestDto requestDto) {
        String accessToken = requestDto.getAccessToken();
        System.out.println("Access Token: " + accessToken);
        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // 요청 생성
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        // 카카오 서버의 응답을 리턴
        String response = restTemplate.postForObject(getInfoUrl, request, String.class);
        System.out.println("응답: " + response);
        return response;
    }
}
