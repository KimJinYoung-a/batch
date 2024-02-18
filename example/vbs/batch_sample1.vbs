option explicit


Function LogWrite(iMsg)
    Dim logFile
    Dim FSO : Set FSO = WScript.CreateObject("Scripting.FileSystemObject")
    ''Dim Shell : Set Shell = WScript.CreateObject("WScript.Shell")
    ''Dim ProgramDir : ProgramDir = Shell.Environment("Process").Item("ProgramFiles")

    Dim LogPath : LogPath = Left(WScript.ScriptFullName,Len(WScript.ScriptFullName)-Len(WScript.ScriptName))
    LogPath = LogPath & "Logs"



    ''Log 폴더생성
    If Not FSO.folderExists(LogPath) Then
        FSO.CreateFolder(LogPath)
    End If

    logFile = Replace(Left(CStr(Now),10),"-","")&".txt"

    ''ForReading-1, ForWriting-2, ForAppending-8
    Dim objFile : Set objFile = FSO.OpenTextFile(LogPath + "\" + logFile,8,True)
    ''Dim objFile : Set objFile = FSO.OpenTextFile(LogPath + "\" + logFile,ForAppending)
    objFile.WriteLine Now & " " & iMsg
    objFile.Close
    Set objFile = Nothing
    Set FSO = Nothing

end Function

Function LogRead(ilogFile)
    Dim logFile
    Dim FSO : Set FSO = WScript.CreateObject("Scripting.FileSystemObject")

    Dim LogPath : LogPath = Left(WScript.ScriptFullName,Len(WScript.ScriptFullName)-Len(WScript.ScriptName))
    LogPath = LogPath & "Logs"

    If (ilogFile<>"") Then
        logFile = ilogFile
    Else
        logFile = Replace(Left(CStr(Now),10),"-","")&".txt"
    End If

    If Not FSO.FileExists(LogPath + "\" + logFile) Then
        Exit function
    End If

    Dim objFile : Set objFile = FSO.OpenTextFile(LogPath + "\" + logFile,1)
    Dim Buf : buf = objFile.readAll

    objFile.Close
    Set objFile = Nothing
    Set FSO = Nothing

    LogRead = Buf
End Function

Function SendReqPost(call_url, sedata)
    dim objHttp, ret_txt, status
    Set objHttp = CreateObject("Msxml2.ServerXMLHTTP")

    on error resume next
    objHttp.Open "POST", call_url, False
    objHttp.setRequestHeader "Connection", "close"
    objHttp.setRequestHeader "Content-Length", Len(sedata)
    objHttp.setRequestHeader "Content-Type", "application/x-www-form-urlencoded"
    objHttp.setTimeouts 5000,90000,90000,90000
    objHttp.Send  sedata

    '지정한 경로의 서버상태값을 가지고 옵니다.
    status = objHttp.status

    '오류가 있거나 (오류가 없을경우 err.number가 0 값을 돌림) status 값이 200 (로딩 성공) 이 아닐경우
    if err.number <> 0 or status <> 200 then
          if status = 404 then
                ret_txt = "[404]존재하지 않는 페이지 입니다."
          elseif status >= 401 and status < 402 then
                ret_txt = "[401]접근이 금지된 페이지 입니다."
          elseif status >= 500 and status <= 600 then
                ret_txt = "[500]내부 서버 오류 입니다."
          else
                ret_txt = "[err]서버가 다운되었거나 올바른 경로가 아닙니다."
          end if
    '오류가 없음 (문서를 성공적으로 로딩함)
    else
          ret_txt = objHttp.ResponseBody
    end if
    on Error Goto 0
    set objHttp = Nothing


    SendReqPost = Trim(BinToText(ret_txt,8192))
end function

Function SendReqGetPlain(call_url, sedata)
    dim objHttp, ret_txt, status
    Set objHttp = CreateObject("Msxml2.ServerXMLHTTP")

    on error resume next
    objHttp.Open "GET", call_url & "?" & sedata , False
'    objHttp.setRequestHeader "Connection", "close"
'    objHttp.setRequestHeader "Content-Length", Len(sedata)
'    objHttp.setRequestHeader "Content-Type", "application/x-www-form-urlencoded"
    objHttp.setTimeouts 5000,90000,90000,90000
    objHttp.Send

    '지정한 경로의 서버상태값을 가지고 옵니다.
    status = objHttp.status

    '오류가 있거나 (오류가 없을경우 err.number가 0 값을 돌림) status 값이 200 (로딩 성공) 이 아닐경우
    if err.number <> 0 or status <> 200 then
          if status = 404 then
                ret_txt = "[404]존재하지 않는 페이지 입니다."
          elseif status >= 401 and status < 402 then
                ret_txt = "[401]접근이 금지된 페이지 입니다."
          elseif status >= 500 and status <= 600 then
                ret_txt = "[500]내부 서버 오류 입니다."
          else
                ret_txt = "[err]서버가 다운되었거나 올바른 경로가 아닙니다."
          end if
    '오류가 없음 (문서를 성공적으로 로딩함)
    else
          ret_txt = objHttp.ResponseBody
    end if
    If (ERR) THEN ret_txt=Err.description
    on Error Goto 0
    set objHttp = Nothing


    SendReqGetPlain = Trim(ret_txt)
end function

Function SendReqGet(call_url, sedata)
    dim objHttp, ret_txt, status
    Set objHttp = CreateObject("Msxml2.ServerXMLHTTP")

    on error resume next
    objHttp.Open "GET", call_url & "?" & sedata , False
'    objHttp.setRequestHeader "Connection", "close"
'    objHttp.setRequestHeader "Content-Length", Len(sedata)
'    objHttp.setRequestHeader "Content-Type", "application/x-www-form-urlencoded"
    objHttp.setTimeouts 5000,90000,90000,90000
    objHttp.Send

    '지정한 경로의 서버상태값을 가지고 옵니다.
    status = objHttp.status

    '오류가 있거나 (오류가 없을경우 err.number가 0 값을 돌림) status 값이 200 (로딩 성공) 이 아닐경우
    if err.number <> 0 or status <> 200 then
          if status = 404 then
                ret_txt = "[404]존재하지 않는 페이지 입니다."
          elseif status >= 401 and status < 402 then
                ret_txt = "[401]접근이 금지된 페이지 입니다."
          elseif status >= 500 and status <= 600 then
                ret_txt = "[500]내부 서버 오류 입니다."
          else
                ret_txt = "[err]서버가 다운되었거나 올바른 경로가 아닙니다."
          end if
    '오류가 없음 (문서를 성공적으로 로딩함)
    else
          ret_txt = objHttp.ResponseBody
    end if
    on Error Goto 0
    set objHttp = Nothing


    SendReqGet = Trim(BinToText(ret_txt,8192))
end function

Function BinToText(varBinData, intDataSizeBytes)
    Const adFldLong = &H00000080
    Const adVarChar = 200

    dim objRS, strV, tmpMsg,isError

    Set objRS = CreateObject("ADODB.Recordset")
    objRS.Fields.Append "txt", adVarChar, intDataSizeBytes, adFldLong
    objRS.Open
    objRS.AddNew
    objRS.Fields("txt").AppendChunk varBinData
    strV=objRS("txt").Value
    BinToText = strV
    objRS.Close
    Set objRS=Nothing
End Function

Function StripTags(htmlDoc)
    Dim rex
    Set rex = new Regexp
    rex.Pattern= "<[^>]+>"
    rex.Global=True
    StripTags =rex.Replace(htmlDoc,"")
    Set rex = Nothing
End Function

Function IsSuccess(iretVal)
    IsSuccess = Left(iretVal,Len("S_OK"))="S_OK"
End Function

Function IsNotExists(iretVal)
    IsNotExists = Left(iretVal,Len("S_NONE"))="S_NONE"
End Function

Function getRetValue(iretVal)
    getRetValue = Mid(iretVal,InStr(iretVal,"|")+1,255)
End Function

Function MailCDO(mailto, mailfrom, mailtitle, mailcontent)

    dim cdoMessage,cdoConfig
    Set cdoConfig = CreateObject("CDO.Configuration")

    '-> 서버 접근방법을 설정합니다
    cdoConfig.Fields.Item("http://schemas.microsoft.com/cdo/configuration/sendusing") = 2 '1 - (cdoSendUsingPickUp)  2 - (cdoSendUsingPort)

    '-> 서버 주소를 설정합니다
    cdoConfig.Fields.Item("http://schemas.microsoft.com/cdo/configuration/smtpserver") = "110.93.128.95"

    '-> 접근할 포트번호를 설정합니다
    cdoConfig.Fields.Item("http://schemas.microsoft.com/cdo/configuration/smtpserverport") = 25

    '-> 접속시도할 제한시간을 설정합니다
    cdoConfig.Fields.Item("http://schemas.microsoft.com/cdo/configuration/smtpconnectiontimeout") = 10

    '-> SMTP 접속 인증방법을 설정합니다
    cdoConfig.Fields.Item("http://schemas.microsoft.com/cdo/configuration/smtpauthenticate") = 1

    '-> SMTP 서버에 인증할 ID를 입력합니다
    cdoConfig.Fields.Item("http://schemas.microsoft.com/cdo/configuration/sendusername") = "MailSendUser"

    '-> SMTP 서버에 인증할 암호를 입력합니다
    cdoConfig.Fields.Item("http://schemas.microsoft.com/cdo/configuration/sendpassword") = "XXXXXXXXXXXX"

    cdoConfig.Fields.Update

    Set cdoMessage = CreateObject("CDO.Message")

    Set cdoMessage.Configuration = cdoConfig

    cdoMessage.To               = mailto
    cdoMessage.From             = mailfrom  '"test@testsvr-am1vgl5" ''
    cdoMessage.SubJect  = mailtitle
    '메일 내용이 텍스트일 경우 cdoMessage.TextBody, html일 경우 cdoMessage.HTMLBody
    cdoMessage.HTMLBody = mailcontent

    cdoMessage.Send

    Set cdoMessage = nothing
    Set cdoConfig = nothing
End Function




'-------------------------------------------
'ex) 파라미터 넘기는 방법
'    sedata : param1=a&param2=b
'-------------------------------------------

dim diffTime
dim otime , orgTim
Dim i, MaxLoop
Dim RetVal, evalCnt, retErrMsg, totSuccCnt, totErrCnt
Dim pLog, pLogAll
Dim CurrDay
MaxLoop = 1
totSuccCnt = 0
CurrDay = Day(Now())

otime = Timer()
orgTim = otime

RetVal = SendReqPost("http://scm.10x10.co.kr/admin/maechul/pgdata/pgdata_process.asp","mode=getonpgdata&redSsnKey=system")
diffTime = FormatNumber(Timer()-otime,4)
pLog = "승인내역 가져오기 INI: "
pLogAll = pLogAll + pLog & VbCrlf
pLogAll = pLogAll + "실행시간 : "& diffTime & "<br>" & replace(RetVal,vbcrlf,"<br>") & "<br>"
pLogAll = pLogAll + "-----------------------------------------------------------------<br>"

otime = Timer()

RetVal = SendReqPost("http://scm.10x10.co.kr/admin/maechul/pgdata/pgdata_process.asp","mode=getonpgdatahp&redSsnKey=system")
diffTime = FormatNumber(Timer()-otime,4)
pLog = "승인내역 가져오기 INI HP: "
pLogAll = pLogAll + pLog & VbCrlf
pLogAll = pLogAll + "실행시간 : "& diffTime & "<br>" & replace(RetVal,vbcrlf,"<br>") & "<br>"
pLogAll = pLogAll + "-----------------------------------------------------------------<br>"

if CurrDay = 2 or CurrDay = 3 then
    otime = Timer()

    RetVal = SendReqPost("http://scm.10x10.co.kr/admin/maechul/pgdata/pgdata_process.asp","mode=getonpgdatahppre&redSsnKey=system")
    RetVal = SendReqPost("http://scm.10x10.co.kr/admin/maechul/pgdata/pgdata_process.asp","mode=getonpgdatahp&redSsnKey=system&gubun=prevmonth")
    diffTime = FormatNumber(Timer()-otime,4)
    pLog = "승인내역 가져오기 INI HP(PREV MONTH): "
    pLogAll = pLogAll + pLog & VbCrlf
    pLogAll = pLogAll + "실행시간 : "& diffTime & "<br>" & replace(RetVal,vbcrlf,"<br>") & "<br>"
    pLogAll = pLogAll + "-----------------------------------------------------------------<br>"
end if

otime = Timer()

RetVal = SendReqPost("http://scm.10x10.co.kr/admin/maechul/pgdata/pgdata_process.asp","mode=getonpgdatauplus&redSsnKey=system")
diffTime = FormatNumber(Timer()-otime,4)
pLog = "승인내역 가져오기 UPLUS: "
pLogAll = pLogAll + pLog & VbCrlf
pLogAll = pLogAll + "실행시간 : "& diffTime & "<br>" & replace(RetVal,vbcrlf,"<br>") & "<br>"
pLogAll = pLogAll + "-----------------------------------------------------------------<br>"

''2016/08/11 추가
otime = Timer()

RetVal = SendReqPost("http://wapi.10x10.co.kr/nPay/jungsanReceive.asp","mode=npayget&redSsnKey=system")
diffTime = FormatNumber(Timer()-otime,4)
pLog = "승인내역 가져오기 NAVERPAY: "
pLogAll = pLogAll + pLog & VbCrlf
pLogAll = pLogAll + "실행시간 : "& diffTime & "<br>" & replace(RetVal,vbcrlf,"<br>") & "<br>"
pLogAll = pLogAll + "-----------------------------------------------------------------<br>"

''2016/12/12 추가
otime = Timer()


RetVal = SendReqPost("http://scm.10x10.co.kr/admin/maechul/pgdata/pgdata_process.asp","mode=getpaycoT&redSsnKey=system")
diffTime = FormatNumber(Timer()-otime,4)
pLog = "승인내역 가져오기 payco: "
pLogAll = pLogAll + pLog & VbCrlf
pLogAll = pLogAll + "실행시간 : "& diffTime & "<br>" & replace(RetVal,vbcrlf,"<br>") & "<br>"
pLogAll = pLogAll + "-----------------------------------------------------------------<br>"


otime = Timer()


RetVal = SendReqPost("http://scm.10x10.co.kr/admin/maechul/pgdata/pgdata_process.asp","mode=getpaycoS&redSsnKey=system")
diffTime = FormatNumber(Timer()-otime,4)
pLog = "정산내역 가져오기 payco: "
pLogAll = pLogAll + pLog & VbCrlf
pLogAll = pLogAll + "실행시간 : "& diffTime & "<br>" & replace(RetVal,vbcrlf,"<br>") & "<br>"
pLogAll = pLogAll + "-----------------------------------------------------------------<br>"


'' 2019-04-29 추가
otime = Timer()

RetVal = SendReqPost("http://scm.10x10.co.kr/admin/maechul/pgdata/pgdata_process.asp","mode=getonpgdatanewkakaopayT&redSsnKey=system")
diffTime = FormatNumber(Timer()-otime,4)
pLog = "승인내역 가져오기 newkakaopay: "
pLogAll = pLogAll + pLog & VbCrlf
pLogAll = pLogAll + "실행시간 : "& diffTime & "<br>" & replace(RetVal,vbcrlf,"<br>") & "<br>"
pLogAll = pLogAll + "-----------------------------------------------------------------<br>"


otime = Timer()


RetVal = SendReqPost("http://scm.10x10.co.kr/admin/maechul/pgdata/pgdata_process.asp","mode=getonpgdatanewkakaopayS&redSsnKey=system")
diffTime = FormatNumber(Timer()-otime,4)
pLog = "정산내역 가져오기 newkakaopay: "
pLogAll = pLogAll + pLog & VbCrlf
pLogAll = pLogAll + "실행시간 : "& diffTime & "<br>" & replace(RetVal,vbcrlf,"<br>") & "<br>"
pLogAll = pLogAll + "-----------------------------------------------------------------<br>"


otime = Timer()

RetVal = SendReqPost("http://wapi.10x10.co.kr/toss/api.asp","mode=settle&redSsnKey=system")
diffTime = FormatNumber(Timer()-otime,4)
pLog = "승인내역 가져오기 TOSS: "
pLogAll = pLogAll + pLog & VbCrlf
pLogAll = pLogAll + "실행시간 : "& diffTime & "<br>" & replace(RetVal,vbcrlf,"<br>") & "<br>"
pLogAll = pLogAll + "-----------------------------------------------------------------<br>"


''===============================================================================================

pLogAll = pLogAll + "총 실행시간 : "& FormatNumber(Timer()-orgTim,4) & "<br>"
pLogAll = pLogAll + "-----------------------------------------------------------------<br>"


Call MailCDO("aaaaa@10x10.co.kr;bbbbb@10x10.co.kr;ccccc@10x10.co.kr;ddddd@gmail.com","admin@10x10.co.kr","승인내역 가져오기 작업보고" & Now(),replace(pLogAll,vbcrlf,"<br>"))
''WScript.Echo pLogAll
''WScript.Echo LogRead("")

WScript.Quit