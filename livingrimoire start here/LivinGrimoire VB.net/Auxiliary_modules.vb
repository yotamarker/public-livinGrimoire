Imports System.Text.RegularExpressions
Imports System.Collections.Generic
Module Auxiliary_modules
    Public Enum enumRegexGrimoire
        email
        timeStamp
        int
        double_num
        repeatedWord
        phone
        trackingID
        IPV4
        domain
        number
        secondlessTimeStamp
        date_stamp
        fullDate
        simpleTimeStamp
    End Enum
    Public Class RegexUtil
        Public regexDictionary As New Dictionary(Of String, String)
        Public Sub New()
            regexDictionary.Add(enumRegexGrimoire.email, "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")
            regexDictionary.Add(enumRegexGrimoire.timeStamp, "[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}")
            regexDictionary.Add(enumRegexGrimoire.simpleTimeStamp, "[0-9]{1,2}:[0-9]{1,2}")
            regexDictionary.Add(enumRegexGrimoire.secondlessTimeStamp, "[0-9]{1,2}:[0-9]{1,2}")
            regexDictionary.Add(enumRegexGrimoire.fullDate, "[0-9]{1,4}/[0-9]{1,2}/[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}")
            regexDictionary.Add(enumRegexGrimoire.date_stamp, "[0-9]{1,4}/[0-9]{1,2}/[0-9]{1,2}")
            regexDictionary.Add(enumRegexGrimoire.double_num, "[-+]?[0-9]*[.,][0-9]*")
            regexDictionary.Add(enumRegexGrimoire.int, "[-+]?[0-9]{1,13}")
            regexDictionary.Add(enumRegexGrimoire.repeatedWord, "\\b([\\w\\s']+) \\1\\b")
            regexDictionary.Add(enumRegexGrimoire.phone, "[0]\\d{9}")
            regexDictionary.Add(enumRegexGrimoire.trackingID, "[A-Z]{2}[0-9]{9}[A-Z]{2}")
            regexDictionary.Add(enumRegexGrimoire.IPV4, "([0-9].){4}[0-9]*")
            regexDictionary.Add(enumRegexGrimoire.domain, "[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")
            regexDictionary.Add(enumRegexGrimoire.number, "\\d+(\\.\\d+)?")

        End Sub
        Public Function ExtractRegex(ByVal regexStr As String, ByVal ear As String) As String
            Dim regex As New Regex(regexStr)
            Dim match As Match = regex.Match(ear)

            If match.Success Then
                Return match.Value
            Else
                Return String.Empty
            End If
        End Function
        Public Function ExtractRegex(ByVal regexStr As enumRegexGrimoire, ByVal ear As String) As String
            Dim regex As New Regex(Me.regexDictionary(regexStr))
            Dim match As Match = regex.Match(ear)

            If match.Success Then
                Return match.Value
            Else
                Return String.Empty
            End If
        End Function
    End Class
    Public Class TimeGate
        Private pause As Integer = 5 ' minutes to keep gate closed
        Private openedGate As Date = DateTime.Now

        Public Sub New(ByVal minutes As Integer)
            MyBase.New()
            Threading.Thread.Sleep(100)
            Me.pause = minutes
        End Sub

        Public Sub New()
            Threading.Thread.Sleep(100)
        End Sub

        Public Sub SetPause(ByVal pause As Integer)
            If pause < 60 AndAlso pause > 0 Then
                Me.pause = pause
            End If
        End Sub

        Public Sub OpenGate()
            ' The gate will stay open for pause minutes.
            Me.openedGate = Me.openedGate.AddMinutes(pause)
        End Sub

        Public Sub Close()
            openedGate = New Date()
        End Sub

        Public Function IsClosed() As Boolean
            Return DateTime.Compare(openedGate, DateTime.Now) < 0
        End Function

        Public Function IsOpen() As Boolean
            Return Not IsClosed()
        End Function

        Public Sub OpenGate(ByVal minutes As Integer)
            Dim now As Date = New Date()
            Me.openedGate = Me.openedGate.AddMinutes(minutes)
        End Sub
    End Class
    Public Class LGFIFO(Of T)
        ' First-in-first-out queue
        Private elements As New List(Of T)()
        Public Function getElements() As List(Of T)
            Return Me.elements
        End Function

        Public Sub setElements(elements As List(Of T))
            Me.elements = elements
        End Sub

        Public Sub Add(item As T)
            elements.Add(item)
        End Sub

        Public Function Size() As Integer
            Return elements.Count
        End Function

        Public Function Peek() As T
            If Size() = 0 Then
                Return Nothing
            End If
            Return elements(0)
        End Function

        Public Function Poll() As T
            If Size() = 0 Then
                Return Nothing
            End If
            Dim result As T = elements(0)
            elements.RemoveAt(0)
            Return result
        End Function

        Public Sub RemoveItem(item As T)
            If elements.Contains(item) Then
                elements.Remove(item)
            End If
        End Sub

        Public Sub Clear()
            elements.Clear()
        End Sub

        Private rand As New Random()

        Public Function GetRandomElement() As T
            If elements.Count = 0 Then
                Return Nothing
            End If
            Return elements(rand.Next(elements.Count))
        End Function

        Public Function Contains(input As T) As Boolean
            Return elements.Contains(input)
        End Function

        Public Function IsEmpty() As Boolean
            Return elements.Count = 0
        End Function

        Public Sub Remove(element As T)
            elements.Remove(element)
        End Sub

        Public Function GetEnumerator() As IEnumerator(Of T)
            Return elements.GetEnumerator()
        End Function
    End Class
    Public Class TimeUtils
        Public Shared Function getCurrentTimeStamp() As String
            ' Get the current time
            Dim currentTime As DateTime = DateTime.Now

            ' Format the time as "HH:mm"
            Dim formattedTime As String = currentTime.ToString("HH:mm")

            ' Return the formatted time
            Return formattedTime
        End Function
        Public Shared Function getMonthAsInt() As Integer
            Dim currentDate As Date = Date.Now
            Dim currentMonth As Integer = DateAndTime.Month(currentDate)
            Return currentMonth
        End Function
        Public Shared Function getDayOfTheMonthAsInt() As Integer
            ' Get the current date
            Dim currentDate As Date = Date.Now

            ' Extract the day of the month
            Dim dayOfMonth As Integer = DateAndTime.Day(currentDate)

            ' Return the day as an integer
            Return dayOfMonth
        End Function
        Public Shared Function GetCurrentYear() As Integer
            Dim currentYear As Integer = DateTime.Now.Year
            Return currentYear
        End Function
        Public Shared Function getMinutes() As String
            ' Get the current time
            Dim currentTime As DateTime = DateTime.Now

            ' Extract the minutes part
            Dim minutes As String = currentTime.ToString("mm")

            ' Return the minutes as a string
            Return minutes
        End Function
        Public Shared Function getSeconds() As String
            ' Get the current time
            Dim currentTime As DateTime = DateTime.Now

            ' Extract the seconds part
            Dim seconds As String = currentTime.ToString("ss")

            ' Return the seconds as a string
            Return seconds
        End Function
        Public Shared Function GetCurrentDayOfWeekAsInt() As Integer
            ' Get the current culture
            Dim myCulture As System.Globalization.CultureInfo = Globalization.CultureInfo.CurrentCulture

            ' Get the day of the week for today
            Dim dayOfWeek As DayOfWeek = myCulture.Calendar.GetDayOfWeek(Date.Today)

            ' Convert to an integer (1 to 7)
            Dim dayNumber As Integer = CInt(dayOfWeek) + 1

            ' Handle Sunday (7) as a special case
            If dayNumber = 8 Then
                dayNumber = 1
            End If

            Return dayNumber
        End Function
        Public Shared Function getDayOfDWeek() As String
            Dim n As Integer = GetCurrentDayOfWeekAsInt()
            Select Case n
                Case 1
                    Return "sunday"
                Case 2
                    Return "monday"
                Case 3
                    Return "tuesday"
                Case 4
                    Return "wednesday"
                Case 5
                    Return "thursday"
                Case 6
                    Return "friday"
                Case 7
                    Return "saturday"
                Case Else
                    Return "Invalid day number"
            End Select
        End Function
        Public Shared Function getSecondsAsInt() As Integer
            Return DateTime.Now.Second
        End Function
        Public Shared Function getMinutesAsInt() As Integer
            Return DateTime.Now.Minute
        End Function
        Public Shared Function getHoursAsInt() As Integer
            Return DateTime.Now.Hour
        End Function
        Public Shared Function getFutureInXMin(ByVal minutes As Integer) As String
            ' Get the current time
            Dim currentTime As DateTime = DateTime.Now

            ' Format the time as "HH:mm"
            Dim formattedTime As String = currentTime.AddMinutes(minutes).ToString("HH:mm")

            ' Return the formatted time
            Return formattedTime
        End Function
        Public Shared Function getPastInXMin(ByVal minutes As Integer) As String
            ' Get the current time
            Dim currentTime As DateTime = DateTime.Now

            ' Format the time as "HH:mm"
            Dim formattedTime As String = currentTime.AddMinutes(minutes * -1).ToString("HH:mm")

            ' Return the formatted time
            Return formattedTime
        End Function
        Public Shared Function TranslateMonth(month1 As Integer) As String
            Dim dMonth As String = ""

            Select Case month1
                Case 1
                    dMonth = "January"
                Case 2
                    dMonth = "February"
                Case 3
                    dMonth = "March"
                Case 4
                    dMonth = "April"
                Case 5
                    dMonth = "May"
                Case 6
                    dMonth = "June"
                Case 7
                    dMonth = "July"
                Case 8
                    dMonth = "August"
                Case 9
                    dMonth = "September"
                Case 10
                    dMonth = "October"
                Case 11
                    dMonth = "November"
                Case 12
                    dMonth = "December"
                Case Else
                    ' Handle invalid month values (optional)
                    dMonth = "Invalid Month"
            End Select

            Return dMonth
        End Function
        Public Shared Function TranslateMonthDay(day1 As Integer) As String
            Dim dday As String = ""

            Select Case day1
                Case 1
                    dday = "first_of"
                Case 2
                    dday = "second_of"
                Case 3
                    dday = "third_of"
                Case 4
                    dday = "fourth_of"
                Case 5
                    dday = "fifth_of"
                Case 6
                    dday = "sixth_of"
                Case 7
                    dday = "seventh_of"
                Case 8
                    dday = "eighth_of"
                Case 9
                    dday = "nineth_of"
                Case 10
                    dday = "tenth_of"
                Case 11
                    dday = "eleventh_of"
                Case 12
                    dday = "twelveth_of"
                Case 13
                    dday = "thirteenth_of"
                Case 14
                    dday = "fourteenth_of"
                Case 15
                    dday = "fifteenth_of"
                Case 16
                    dday = "sixteenth_of"
                Case 17
                    dday = "seventeenth_of"
                Case 18
                    dday = "eighteenth_of"
                Case 19
                    dday = "nineteenth_of"
                Case 20
                    dday = "twentyth_of"
                Case 21
                    dday = "twentyfirst_of"
                Case 22
                    dday = "twentysecond_of"
                Case 23
                    dday = "twentythird_of"
                Case 24
                    dday = "twentyfourth_of"
                Case 25
                    dday = "twentyfifth_of"
                Case 26
                    dday = "twentysixth_of"
                Case 27
                    dday = "twentyseventh_of"
                Case 28
                    dday = "twentyeighth_of"
                Case 29
                    dday = "twentynineth_of"
                Case 30
                    dday = "thirtyth_of"
                Case 31
                    dday = "thirtyfirst_of"
                Case Else
                    ' Handle invalid day values (optional)
                    dday = "Invalid_day"
            End Select
            Return dday
        End Function
        Public Shared Function SmallToBig(ParamArray a() As Integer) As Boolean
            For i As Integer = 0 To a.Length - 2
                If Not (a(i) < a(i + 1)) Then
                    Return False
                End If
            Next
            Return True
        End Function
        Public Shared Function IsDayTime() As Boolean
            Dim hour As Integer = getHoursAsInt()
            Return hour > 5 AndAlso hour < 19
        End Function
        Public Shared Function PartOfDay() As String
            Dim hour As Integer = getHoursAsInt()

            If SmallToBig(5, hour, 12) Then
                Return "morning"
            ElseIf SmallToBig(11, hour, 17) Then
                Return "afternoon"
            ElseIf SmallToBig(16, hour, 21) Then
                Return "evening"
            Else
                Return "night"
            End If
        End Function
        Public Shared Function IsNight() As Boolean
            Dim hour As Integer = getHoursAsInt()
            Return hour > 20 OrElse hour < 6
        End Function
        Public Shared Function getYesterday() As String
            Dim n As Integer = GetCurrentDayOfWeekAsInt()
            Select Case n
                Case 2
                    Return "sunday"
                Case 3
                    Return "monday"
                Case 4
                    Return "tuesday"
                Case 5
                    Return "wednesday"
                Case 6
                    Return "thursday"
                Case 7
                    Return "friday"
                Case 1
                    Return "saturday"
                Case Else
                    Return "Invalid day number"
            End Select
        End Function
        Public Shared Function getTomorrow() As String
            Dim n As Integer = GetCurrentDayOfWeekAsInt()
            Select Case n
                Case 7
                    Return "sunday"
                Case 1
                    Return "monday"
                Case 2
                    Return "tuesday"
                Case 3
                    Return "wednesday"
                Case 4
                    Return "thursday"
                Case 5
                    Return "friday"
                Case 6
                    Return "saturday"
                Case Else
                    Return "Invalid day number"
            End Select
        End Function
        Public Shared Function GetLocalTimeZone() As String
            Dim localTimeZone As TimeZoneInfo = TimeZoneInfo.Local
            Return localTimeZone.Id
        End Function
        Public Shared Function FindDay(month As Integer, day As Integer, year As Integer) As String
            ' Validate input: Ensure day is within a valid range (1 to 31).
            If day > 31 Then
                Return ""
            End If

            ' Check specific months with 30 days.
            If day > 30 AndAlso (month = 4 OrElse month = 6 OrElse month = 9 OrElse month = 11) Then
                Return ""
            End If

            ' Check February for leap year.
            If month = 2 Then
                If IsLeapYear(year) Then
                    If day > 29 Then
                        Return ""
                    End If
                ElseIf day > 28 Then
                    Return ""
                End If
            End If

            Dim localDate As Date = New Date(year, month, day)
            Dim dayOfWeek As DayOfWeek = localDate.DayOfWeek
            Return dayOfWeek.ToString().ToLower()
        End Function
        Public Shared Function NextDayOnDate(dayOfMonth As Integer) As String
            ' Get the weekday on the next dayOfMonth.
            Dim today As Integer = DateTime.Now.Day
            Dim nextMonth As Integer = DateTime.Now.Month
            Dim nextYear As Integer = DateTime.Now.Year

            If today <= dayOfMonth Then
                Return FindDay(nextMonth, dayOfMonth, nextYear)
            ElseIf Not (nextMonth = 12) Then ' December?
                Return FindDay(nextMonth + 1, dayOfMonth, nextYear)
            End If

            Return FindDay(1, dayOfMonth, nextYear + 1)
        End Function
        Public Shared Function IsLeapYear(year As Integer) As Boolean
            Dim b1 As Boolean

            ' Divisible by 4.
            b1 = (year Mod 4 = 0)

            ' Divisible by 4, not by 100, or divisible by 400.
            Return b1 AndAlso (year Mod 100 <> 0 OrElse year Mod 400 = 0)
        End Function
        Public Shared Function getCurrentMonthName() As String
            Return TranslateMonth(getMonthAsInt)
        End Function
        Public Shared Function getCurrentMonthDay() As String
            Return TranslateMonthDay(getDayOfTheMonthAsInt)
        End Function
    End Class
End Module
