#### WAS 구현
##### 기능 구현 목록
1. Host 헤더 분석.  
   HttpRequest 객체를 이용하여 request 헤더를 파싱*분석하고   
   생성된 HttpRequest 객체를 이용하여 처리가능한 HostBasedApplicationContext.  
   를 찾아 해당 ApplicationContext의 데이터를 이용하여 처리하도록 구현하였습니다.   

2. 설정 파일 관리.  
   설정 파일의 경우, src/main/resources/application.json 파일로   
   따로 분리하여 관리하고 있습니다.

3. 403, 404, 500 오류 처리.  
   RequestProcessor에서 해당 오류와 관련된 에러페이지를 응답하도록 처리하였습니다.
   
4. 다음과 같은 보안 규칙 적용.  
   * docBase 상위 디렉토리에 접근
   * .exe 파일 요청시
    
   static resource를 처리하는 StaticResourceServlet에서   
   상위 디렉토리 접근을 의미하는 ../ 문자열이 uri에 존재하는 경우, .exe 파일 요청시   
   Forbidden 응답처리하였습니다.

5. logback 프레임워크 적용.  
   slf4j && logback 프레임워크를 이용하여 로깅처리하였습니다.

6. 간단한 Servlet 등록 및 구현체 동작.  
   HostBasedApplicationContext 초기화시 특정 패키지 하위의 Servlet 인터페이스를   
   상속받아 구현한 클래스들을 찾아 패키지 & java 파일 명으로 등록하여   
   해당하는 request 요청시 처리할 수 있도록 하였습니다. 

7. 현재 시간 출력하는 SimpleServlet 구현체 구현.  
   com.nhn.assignment.application.simple 패키지 하위에 SimpleServlet을 구현하여   
   현재 시간을 응답하도록 하였습니다.
