# AtlasValue

REST API that allows to manage atlases. 

## How to run:
- Pre-installation of Maven and jdk17 is required
- Clone the Git repository: `https://github.com/PavelBui/AtlasValue` (main branch)
- Run the command `mvn clean package` in the root folder
- Run the command `java -jar TaskTrackTemplate-1.0-SNAPSHOT.jar` in the `target` folder

## Parameters
- **Port** - 8081
- **URL** - http://localhost:8081
- **Swagger** - http://localhost:8081/swagger-ui/index.html

## Atlas endpoints
- **Create atlas** - POST request http://localhost:8081/atlas
    - `RequestBody: AtlasDto`
    - `ResponseBody: AtlasDto`
- **Get atlas** - GET request http://localhost:8081/atlas/{id}
    - `PathVariable: atlas id`
    - `ResponseBody: AtlasDto`
- **Get all atlases** - GET request http://localhost:8081/atlas
    - `ResponseBody: List of AtlasDto`
- **Update atlas** - PUT request http://localhost:8081/atlas/{id}
    - `PathVariable: atlas id`
    - `RequestBody: AtlasDto`
    - `ResponseBody: AtlasDto`
- **Delete atlas** - DELETE request http://localhost:8081/atlas/{id}
    - `PathVariable: atlas id`
    - `ResponseBody: String` (Atlas was deleted successfully)

## AtlasDto (example)
```json
{
    "id": 0,
    "title": "string",
    "time_period": "string",
    "description": "string",
    "class": "string",
    "year": "integer",
    "country": "string",
    "publisher": "string",
    "circulation": "integer"
}
```

## Test Mermaid
```mermaid
---
title: Booking statuses
---
graph TD
  TA[Booking was created by Admission]:::noBorder-.->A(ACTIVE)
  TI[Booking was deactivated or deleted by Admission]:::noBorder-.->I(INACTIVE)
  TM[Examinee has not checked in before test session started]:::noBorder-.->M(MISSED)
  TCI[Examinee has been successfully checked in to the test session]:::noBorder-.->CI(CHECKED_IN)
  A-- Booking was deactivated or deleted by TestMe -->C(CLOSED);
  A-->I;
  A-->M;
  A-->CI;
  classDef noBorder stroke-width:0,fill:transparent;
```

```mermaid
---
title: Test session statuses
---
graph TD
  TC["Test session created by admin, and can be seen in Tests catalogue by Examinees (only read test session extended info"]:::noBorder-.->C(Created)
  TD["Test session was deactivated before booking time started, or after booking started but before anyone applied booking (test session deactivation affects nobody)"]:::noBorder-.->D(Deactivated)
  TA["Test session booking time started, session can be seen in catalogue. Examinees can book test session"]:::noBorder-.->A(Active)
  TCL["Test session was manually deleted Before test session start date by admin WITH active booking inside or was automatically closed by server"]:::noBorder-.->CL(Closed)
  TR["Test session booking time Expired. Test session Have active bookings. No more bookings allowed, test session is not more visible in catalogue"]:::noBorder-.->R(Ready)
  TS["Operator started interaction with test session entity from Operator UI (started adding PC to the test session)"]:::noBorder-.->S(Started)
  TCI["Operator started adding Examines to the test session"]:::noBorder-.->CI(Check-In)
  TST["Test session was manually terminated by Operator (for Example massive technical issue)"]:::noBorder-.->ST(Stopped)
  TIP["Test itself was started"]:::noBorder-.->IP(In-progress)
  TAC["Test was finished by cronjob"]:::noBorder-.->AC(Autoclossed)
  TF["Test was finished by operator"]:::noBorder-.->F(Finished)
  C-->D;
  C-->A;
  A-->D;
  A-->CL;
  A-->R;
  R-->CL;
  R-->S;
  S-->CL;
  S-->CI;
  CI-->CL;
  S-->ST;
  CI-->ST;
  CI-->IP;
  IP-->AC;
  IP-->F;
  RA["Red arrows - if test session was closed by cronjob at end of the day, if test session WAS NOT started by operator manually"]:::noBorder
  linkStyle 18 stroke:#ff0000, stroke-width:2px;
  linkStyle 20 stroke:#ff0000, stroke-width:2px;
  classDef noBorder stroke-width:0,fill:transparent;
```

```mermaid
---
title: Test results statuses
---
graph TD
  TTRC[Test session was closed by operator because of cheating]:::noBorder-.->TRC(TEST_RESULT_CHEATING)
  TTRI[TEst session was closed by operator because of tech. issue]:::noBorder-.->TRI(TEST_RESULT_ISSUE)
  TP[Test session is still in progress, no test results can be found]:::noBorder-.->P(Pending)
  TPa["Test session was completed (as process)"]:::noBorder-.->Pa(Pass)
  TF["Test session was failed (as process)"]:::noBorder-.->F(Failed)
  P-- "Test result expired by timeout (4 hours)" -->E(Expired);
  P-->Pa;
  P-->F;
  classDef noBorder stroke-width:0,fill:transparent;
```

```mermaid
---
title: Dependency
---
graph TD
  subgraph Booking
    AB(Active)-- Booking was deactivated or deleted by TestMe -->CB(Closed);
    AB-->IB(Inactive);
    AB-->MB(Missed);
    AB-->CIB(Checked-in);
  end
  subgraph Test session
    CTS(Created)-->DTS(Deactivated);
    CTS-->ATS(Active);
    ATS-->DTS;
    ATS-->CLTS(Closed);
    ATS-->RTS(Ready);
    RTS-->CLTS;
    RTS-->STS(Started);
    STS-->CLTS;
    STS-->CITS(Check-In);
    CITS-->CLTS;
    STS-->STTS(Stopped);
    CITS-->STTS;
    CITS-->IPTS(In-progress);
    IPTS-->ACTS(Autoclossed);
    IPTS-->FTS(Finished);
  end  
  ATS-.->AB
  CTS-.->CB
  IPTS-.->MB
  classDef noBorder stroke-width:0,fill:transparent;
```


```mermaid
zenuml
  Admin->Admin_UI_API: Create Test Center
  Admin_UI_API->TestMe: PUSH center/DTO
  Admin->Admin_UI_API: Create Room
  Admin_UI_API->TestMe: PUSH room/DTO
  Admin->Admin_UI_API: Create Test
  Admin_UI_API->TestMe: PUSH test/DTO
  TestMe->Examinator: Get tests
  Admin->Admin_UI_API: Create Test Session
  Admin_UI_API->TestMe: PUSH testSession/DTO
  TestMe->TestMe: Activate Test Session
  Examinee->Admission: Create Booking
  Admission->TestMe: PUSH booking/DTO
  TestMe->TestMe: Test Session is Ready
  TestMe->TestMe: Test Session Ready to Start  
  Operator->Operator_UI_API: Check-In Examinee
  Operator_UI_API->TestMe: Check-In Examinee
  Operator->Operator_UI_API: Start Test Session
  Operator_UI_API->TestMe: Start Test Session
  Examinee->Desktop_Client_API: Check-In
  Desktop_Client_API->TestMe: Check-In
  TestMe->TestMe: Test Session is Finished
```