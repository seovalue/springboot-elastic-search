package me.elastic.domain;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 테스트 환경 - 테스트 context를 제공해줬음 (기존)
 * elasticSearch용 테스트 context는 Springboot가 지원하지 않는다.
 * <p>
 * -- 테스트 방법 --
 * 테스트용 elasticSearch를 구축하는 방법
 * Mock을 쓴다는 것 -> elasticSearch랑 연동되는것까지 확인하는 것이 아님 /
 * elasticSearch와의 요청/응답 후 우리 코드 내에서만 테스트
 * -> 이것만으로도 테스트에 의미가 있다면 이렇게만 해도 됨.
 *
 * hibernate 등은 h2라는 테스트 컨텍스트 구축 라이브러리가 있지만, elasticsearch는 없거나 아직 못찾!
 * inmemory-based elasticsearch test engine이 있으면 가져다 쓰고 없으면 mock?????
 */
class PostEsRepositoryTest {

    @Autowired
    private PostEsRepository postEsRepository;

    // TODO 어떻게 의존성을 주입해야할 지 모르겠다.

}