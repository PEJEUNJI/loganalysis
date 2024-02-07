프로젝트 구조는 아래와 같습니다.


dktech_homework
│
├── src
│   └── main
│       └── java
│           ├── com
│           │   └── loganalysis
│           │       └── service
│           │           ├── dto
│           │           │   └── LogAnalysisOutputDto.java
│           │           ├── LogAnalysisService.java
│           │           └── LogPrinterService.java
│           ├── com
│           │   └── loganalysis
│           │       └── utils
│           │           ├── ConfigReader.java
│           │           ├── DataExtractorUtil.java
│           │           ├── FileReaderUtil.java
│           │           └── FileWriterUtil.java
│           └── com
│               └── loganalysis
│                   └── Main.java
├── test
│   └── java
│       └── com
│           └── loganalysis
│               └── service
│                   └── LogAnalysisServiceTest.java
└── logs
    ├── input.log
    └── output.log

LogAnalysisService
: 데이터 분석을 위한 클래스[]안의 문자열을 추출
analyze method 기능 : 각 라인을 분석하여 정보 추출 후, []안의 문자열을 추출
첫 괄호 안의 데이터가 200  경우에만 분석 수행
1. "apikey=" 다음에 나오는 '&' 문자가 나타날 때까지의 모든 문자열을 추출
* apikey별 호출 횟수 카운트
2. "search/" 다음에 나오는 '?' 문자가 나타날 때까지의 모든 문자열을 추출
* apiserviceid별 호출 횟수 카운트
3. 브라우저 종류별 호출 횟수 카운트

LogPrinterService
: 파일 출력을 위해 조건 데이터 추출 및 파일 출력
1. 가장 많이 호출된 apikey 찾기
2. value값이 큰 순서로, 상위 3개 ApiServiceId 리스트 구하기
3. 브라우저 종류별 비율 계산

LogAnalysisOutputDto
1. apiKey 종류별 호출 횟수를 저장하는 map
2. 브라우저 종류별 호출 횟수를 저장하는 map
3. apiServiceId 호출 횟수를 저장하는 map
4. 가장 많이 호출된 apiKey를 저장
5. 상위 apiserviceid 리스트와 그에 대응하는 값들을 저장하는 map
6. 브라우저별 비율을 저장하는 map 


Util 클래스
1. ConfigReader : config.properties 에서 읽어온 경로 정보를 저장
2. DataExtractorUtil : 입력된 pattern에 맞게 데이터 추출
3. FileReaderUtil : 데이터를 읽어와서 해당 파일의 각 라인을 스트림으로 반환
4. FileWriterUtil : 결과데이터 write후 파일 추출


Main 클래스
1. LogAnalysisService를 이용하여 로그 데이터 분석
2. LogPrinterService를 이용하여 결과 출력

