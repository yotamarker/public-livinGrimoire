Imports System.Text.RegularExpressions
Imports System.Collections.Generic
Imports System.Text

Module Auxiliary_modules
    Public Class DeepCopier
        Public Function DeepCopyStringList(originalList As List(Of String)) As List(Of String)
            ' Create a new list and copy each element from the original list.
            Dim copiedList As New List(Of String)(originalList.Count)
            For Each item As String In originalList
                copiedList.Add(item)
            Next

            ' Return the deep-copied list.
            Return copiedList
        End Function
        Public Function DeepCopyIntList(originalList As List(Of Integer)) As List(Of Integer)
            ' Create a new list and copy each element from the original list.
            Dim copiedList As New List(Of Integer)(originalList.Count)
            For Each item As Integer In originalList
                copiedList.Add(item)
            Next

            ' Return the deep-copied list.
            Return copiedList
        End Function

    End Class
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

        Public Sub openGateforNSeconds(ByVal n As Integer)
            ' The gate will stay open for n seconds.
            Me.openedGate = Me.openedGate.AddSeconds(n)
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
    Public Class AlgDispenser
        ' Super class to output an algorithm out of a selection of algorithms
        Private algs As New List(Of Algorithm)()
        Private activeAlg As Integer = 0
        Private rand As New Random()

        Public Sub New(ParamArray algorithms As Algorithm())
            For Each alg As Algorithm In algorithms
                algs.Add(alg)
            Next
        End Sub

        Public Function AddAlgorithm(alg As Algorithm) As AlgDispenser
            ' Builder pattern
            algs.Add(alg)
            Return Me
        End Function

        Public Function DispenseAlgorithm() As Algorithm
            Return algs(activeAlg)
        End Function

        Public Function RndAld() As Algorithm
            ' Return a random algorithm
            Return algs(rand.Next(algs.Count))
        End Function

        Public Sub MoodAlg(mood As Integer)
            ' Set output algorithm based on number representing mood
            If mood >= 0 AndAlso mood < algs.Count Then
                activeAlg = mood
            End If
        End Sub

        Public Sub AlgUpdate(mood As Integer, alg As Algorithm)
            ' Update an algorithm
            If mood >= 0 AndAlso mood < algs.Count Then
                algs(mood) = alg
            End If
        End Sub

        Public Sub AlgRemove(mood As Integer)
            ' Remove an algorithm
            If mood >= 0 AndAlso mood < algs.Count Then
                algs.RemoveAt(mood)
            End If
        End Sub

        Public Sub CycleAlg()
            activeAlg += 1
            If activeAlg = algs.Count Then
                activeAlg = 0
            End If
        End Sub
    End Class
    ' A priority queue without repeating elements
    Public Class UniqueItemsPriorityQue
        Inherits LGFIFO(Of String)

        Public Overloads Sub Add(item As String)
            If Not MyBase.Contains(item) Then
                MyBase.Add(item)
            End If
        End Sub

        Public Function Peak() As String
            Dim temp As String = MyBase.Peek()
            If temp Is Nothing Then
                Return ""
            End If
            Return temp
        End Function

        Public Function StrContainsResponse(str As String) As Boolean
            Dim result As Boolean = False
            For Each tempStr As String In Me.getElements
                If str.Contains(tempStr) Then
                    result = True
                    Exit For
                End If
            Next
            Return result
        End Function
    End Class
    ' Items in the queue are unique and do not repeat
    ' The size of the queue is limited
    Public Class UniqueItemSizeLimitedPriorityQueue
        Inherits UniqueItemsPriorityQue

        Private _limit As Integer = 5

        Public Property Limit As Integer
            Get
                Return _limit
            End Get
            Set(value As Integer)
                _limit = value
            End Set
        End Property

        Public Overloads Sub Add(item As String)
            If MyBase.Size = Limit Then
                MyBase.Poll()
            End If
            MyBase.Add(item)
        End Sub

        Public Overloads Function Poll() As String
            Dim temp As String = MyBase.Poll()
            If temp Is Nothing Then
                Return ""
            End If
            Return temp
        End Function

        Public Function GetRNDElement() As String
            Dim temp As String = MyBase.GetRandomElement
            If temp Is Nothing Then
                Return ""
            End If
            Return temp
        End Function

        Public Function GetAsList() As List(Of String)
            Return getElements()
        End Function
    End Class
    ' A priority queue without repeating elements
    ' The size of the queue is limited
    Public Class RefreshQ
        Inherits UniqueItemSizeLimitedPriorityQueue

        Public Overloads Sub RemoveItem(item As String)
            MyBase.getElements.Remove(item)
        End Sub

        Public Overloads Sub Add(item As String)
            ' FILO
            If MyBase.Contains(item) Then
                RemoveItem(item)
            End If
            MyBase.Add(item)
        End Sub
        Public Sub Stuff(data As String)
            ' FILO 1st in last out
            If MyBase.Size() = Me.Limit Then
                MyBase.Poll()
            End If
            Me.getElements.Add(data)
        End Sub

    End Class
    Public Class AnnoyedQue
        Private q1 As New RefreshQ()
        Private q2 As New RefreshQ()
        Private stuffedQue As New RefreshQ()

        Public Sub New(queLim As Integer)
            q1.Limit = queLim
            q2.Limit = queLim
            stuffedQue.Limit = queLim
        End Sub

        Public Sub Learn(ear As String)
            If q1.Contains(ear) Then
                q2.Add(ear)
                stuffedQue.Stuff(ear)
                Return
            End If
            q1.Add(ear)
        End Sub

        Public Function IsAnnoyed(ear As String) As Boolean
            Return q2.StrContainsResponse(ear)
        End Function

        Public Sub Reset()
            ' Insert unique throwaway strings to reset the state
            For i As Integer = 0 To q1.Limit - 1
                Learn("throwaway_string_" & i)
            Next
        End Sub
        Public Function AnnoyedLevel(ear As String, level As Integer) As Boolean
            Dim count As Integer = 0
            For Each item As String In Me.stuffedQue.getElements
                If item.Equals(ear) Then
                    count += 1
                End If
            Next
            Return count > level
        End Function

    End Class

    Public Class AXCmdBreaker
        ' Separate command parameter from the command
        Public conjuration As String

        Public Sub New(ByVal conjuration As String)
            Me.conjuration = conjuration
        End Sub

        Public Function ExtractCmdParam(ByVal s1 As String) As String
            If s1.Contains(conjuration) Then
                Return s1.Replace(conjuration, "").Trim()
            End If
            Return ""
        End Function
    End Class
    Public Class AXFunnel
        ' funnel many inputs to fewer or one input
        ' allows using command variations in skills
        Private dic As New Dictionary(Of String, String)()
        Private defaultStr As String = "default"

        Public Sub New()
            ' Constructor initializes the dictionary and default value
            dic = New Dictionary(Of String, String)()
            defaultStr = "default"
        End Sub

        Public Sub SetDefault(ByVal defaultStr As String)
            ' Set the default value
            Me.defaultStr = defaultStr
        End Sub

        Public Function AddKV(ByVal key As String, ByVal value As String) As AXFunnel
            ' Add key-value pair to the dictionary
            dic(key) = value
            Return Me
        End Function

        Public Function AddK(ByVal key As String) As AXFunnel
            ' Add key with default value to the dictionary
            dic(key) = defaultStr
            Return Me
        End Function

        Public Function Funnel(ByVal key As String) As String
            ' Get value from dictionary or return the key itself as default
            Return If(dic.ContainsKey(key), dic(key), key)
        End Function

        Public Function FunnelOrEmpty(ByVal key As String) As String
            ' Get value from dictionary or return ""
            Return If(dic.ContainsKey(key), dic(key), "")
        End Function
    End Class
    Public Class AXGamification
        ' This auxiliary module can add fun to tasks, skills, and abilities simply by
        ' tracking their usage and maximum use count.
        Private counter As Integer = 0
        Private max As Integer = 0

        Public Function GetCounter() As Integer
            Return counter
        End Function

        Public Function GetMax() As Integer
            Return max
        End Function

        Public Sub ResetCount()
            counter = 0
        End Sub

        Public Sub ResetAll()
            max = 0
            counter = 0
        End Sub

        Public Sub Increment()
            counter += 1
            If counter > max Then
                max = counter
            End If
        End Sub

        Public Sub IncrementBy(ByVal n As Integer)
            counter += n
            If counter > max Then
                max = counter
            End If
        End Sub

        Public Function Reward(ByVal cost As Integer) As Boolean
            ' Game grind points used for rewards
            ' Consumables, items, or upgrades – this makes games fun
            If cost > counter Then
                Return False
            End If
            counter -= cost
            Return True
        End Function

        Public Function Surplus(ByVal cost As Integer) As Boolean
            ' Has surplus for reward?
            If cost > counter Then
                Return False
            End If
            Return True
        End Function
    End Class
    Public Class AXKeyValuePair
        Private key As String = ""
        Private value As String = ""

        Public Sub New()
        End Sub

        Public Sub New(ByVal key As String, ByVal value As String)
            Me.key = key
            Me.value = value
        End Sub

        Public Function GetKey() As String
            Return key
        End Function

        Public Sub SetKey(ByVal key As String)
            Me.key = key
        End Sub

        Public Function GetValue() As String
            Return value
        End Function

        Public Sub SetValue(ByVal value As String)
            Me.value = value
        End Sub

        Public Overrides Function ToString() As String
            Return key & ";" & value
        End Function
    End Class
    Public Class TrGEV3
        ' Advanced boolean gates with internal logic.
        ' These ease connecting common logic patterns, acting as triggers.

        Public Sub Reset()
            ' Reset logic gate state.
            ' Implement your reset logic here.
        End Sub

        Public Sub Input(ByVal ear As String, ByVal skin As String, ByVal eye As String)
            ' Process input data.
            ' Implement your input logic here.
        End Sub

        Public Function Trigger() As Boolean
            ' Determine whether the trigger condition is met.
            ' Implement your trigger logic here.
            Return False
        End Function
    End Class
    Public Class TrgTolerance
        Inherits TrGEV3
        ' This boolean gate will return true until depletion or reset.
        Private repeats As Integer = 0
        Private maxrepeats As Integer ' Recommended value: 2

        Public Sub New(ByVal maxrepeats As Integer)
            Me.maxrepeats = maxrepeats
        End Sub

        Public Sub SetMaxrepeats(ByVal maxrepeats As Integer)
            Me.maxrepeats = maxrepeats
            Reset()
        End Sub

        Public Overloads Sub Reset()
            ' Refill trigger
            repeats = maxrepeats
        End Sub

        Public Overloads Function Trigger() As Boolean
            ' Will return true until depletion or reset.
            repeats -= 1
            If repeats > 0 Then
                Return True
            End If
            Return False
        End Function

        Public Sub Disable()
            repeats = 0
        End Sub
    End Class
    Public Class AXLearnability
        Private algSent As Boolean = False
        ' Problems that may result because of the last deployed algorithm:
        Public defcons As HashSet(Of String) = New HashSet(Of String)()
        ' Major chaotic problems that may result because of the last deployed algorithm:
        Public defcon5 As HashSet(Of String) = New HashSet(Of String)()
        ' Goals the last deployed algorithm aims to achieve:
        Public goals As HashSet(Of String) = New HashSet(Of String)()
        ' How many failures / problems till the algorithm needs to mutate (change)
        Public trgTolerance As TrgTolerance

        Public Sub New(tolerance As Integer)
            trgTolerance = New TrgTolerance(tolerance)
            trgTolerance.Reset()
        End Sub

        Public Sub pendAlg()
            ' An algorithm has been deployed
            ' Call this method when an algorithm is deployed (in a DiSkillV2 object)
            algSent = True
            trgTolerance.Trigger()
        End Sub

        Public Sub pendAlgWithoutConfirmation()
            ' An algorithm has been deployed
            algSent = True
            ' No need to await for a thank you or check for goal manifestation :
            ' trgTolerance.trigger()
            ' Using this method instead of the default "pendAlg" is the same as
            ' giving importance to the stick and not the carrot when learning
            ' This method is mostly fitting workplace situations
        End Sub

        Public Function mutateAlg(input As String) As Boolean
            ' Recommendation to mutate the algorithm? true/false
            If Not algSent Then Return False ' No alg sent => no reason to mutate
            If goals.Contains(input) Then
                trgTolerance.Reset()
                algSent = False
                Return False
            End If
            ' Goal manifested; the sent algorithm is good => no need to mutate the alg
            If defcon5.Contains(input) Then
                trgTolerance.Reset()
                algSent = False
                Return True
            End If
            ' ^ Something bad happened probably because of the sent alg
            ' Recommend alg mutation
            If defcons.Contains(input) Then
                algSent = False
                Dim mutate As Boolean = Not trgTolerance.Trigger()
                If mutate Then
                    trgTolerance.Reset()
                End If
                Return mutate
            End If
            ' ^ Negative result, mutate the alg if this occurs too much
            Return False
        End Function

        Public Sub resetTolerance()
            ' Use when you run code to change algorithms regardless of learnability
            trgTolerance.Reset()
        End Sub
    End Class

    Public Class AXLHousing
        Public Function Decorate(ByVal str1 As String) As String
            ' Override me
            Return ""
        End Function
    End Class
    Public Class LGTypeConverter
        Private r1 As New RegexUtil()

        Public Function ConvertToInt(ByVal v1 As String) As Integer
            Dim temp As String = r1.ExtractRegex(enumRegexGrimoire.int, v1)
            If temp = "" Then
                Return 0
            End If
            Return Integer.Parse(temp)
        End Function

        Public Function ConvertToDouble(ByVal v1 As String) As Double
            Dim temp As String = r1.ExtractRegex(enumRegexGrimoire.double_num, v1)
            If temp = "" Then
                Return 0.0
            End If
            Return Double.Parse(temp)
        End Function
    End Class
    Public Class DrawRnd
        ' Draw a random element, then remove said element.
        Private strings As New List(Of String)()
        Private stringsSource As New List(Of String)()
        Private rand As New Random()

        Public Sub New(ParamArray values As String())
            For Each value As String In values
                strings.Add(value)
                stringsSource.Add(value)
            Next
        End Sub

        Public Sub AddElement(ByVal element As String)
            strings.Add(element)
            stringsSource.Add(element)
        End Sub

        Public Function Draw() As String
            If strings.Count = 0 Then
                Return ""
            End If

            Dim x As Integer = rand.Next(strings.Count)
            Dim element As String = strings(x)
            strings.RemoveAt(x)
            Return element
        End Function

        Public Function renewableDraw() As String
            If strings.Count = 0 Then
                Reset()
            End If

            Dim x As Integer = rand.Next(strings.Count)
            Dim element As String = strings(x)
            strings.RemoveAt(x)
            Return element
        End Function

        Public Function GetSimpleRNDNum(ByVal bound As Integer) As Integer
            ' Return 0 to bound-1
            Return rand.Next(bound)
        End Function

        Private tc As New LGTypeConverter()

        Public Function DrawAsInt() As Integer
            If strings.Count = 0 Then
                Return 0
            End If

            Dim x As Integer = rand.Next(strings.Count)
            Dim element As String = strings(x)
            strings.RemoveAt(x)
            Return tc.ConvertToInt(element)
        End Function

        Public Sub Reset()
            Dim dc As New DeepCopier()
            strings = dc.DeepCopyStringList(stringsSource)
        End Sub

        Public Function IsEmptied() As Boolean
            Return strings.Count = 0
        End Function
        Public Sub ResetIfEmpty()
            If IsEmptied() Then
                Reset()
            End If
        End Sub

        Public Function ContainsElement(element As String) As Boolean
            Return stringsSource.Contains(element)
        End Function

        Public Function CurrentlyContainsElement(element As String) As Boolean
            Return strings.Contains(element)
        End Function

        Public Sub RemoveItem(element As String)
            strings.Remove(element)
        End Sub
    End Class
    Public Class DrawRndDigits
        Private strings As New List(Of Integer)()
        Private stringsSource As New List(Of Integer)()
        Private rand As New Random()

        Public Sub New(ParamArray values As Integer())
            For Each value As Integer In values
                strings.Add(value)
                stringsSource.Add(value)
            Next
        End Sub

        Public Sub AddElement(element As Integer)
            strings.Add(element)
            stringsSource.Add(element)
        End Sub

        Public Function Draw() As Integer
            If strings.Count = 0 Then
                Return -1
            End If

            Dim x As Integer = rand.Next(strings.Count)
            Dim element As Integer = strings(x)
            strings.RemoveAt(x)
            Return element
        End Function

        Public Function GetSimpleRNDNum(bound As Integer) As Integer
            ' Return a random integer in the range 0 to bound-1
            Return rand.Next(bound)
        End Function

        Public Sub Reset()
            Dim dc As New DeepCopier()
            strings = dc.DeepCopyIntList(stringsSource)
        End Sub
        Public Function IsEmptied() As Boolean
            Return strings.Count = 0
        End Function
        Public Sub ResetIfEmpty()
            If IsEmptied() Then
                Reset()
            End If
        End Sub

        Public Function ContainsElement(element As Integer) As Boolean
            Return stringsSource.Contains(element)
        End Function

        Public Function CurrentlyContainsElement(element As Integer) As Boolean
            Return strings.Contains(element)
        End Function

        Public Sub RemoveItem(element As Integer)
            strings.Remove(element)
        End Sub
    End Class
    Public Class Responder
        ' Simple random response dispenser
        Private responses As New List(Of String)()
        Private rand As New Random()

        Public Sub New(ParamArray replies As String())
            For Each reply As String In replies
                responses.Add(reply)
            Next
        End Sub

        Public Function GetAResponse() As String
            If responses.Count = 0 Then
                Return ""
            End If
            Return responses(rand.Next(responses.Count))
        End Function

        Public Function ResponsesContainsStr(ByVal str As String) As Boolean
            Return responses.Contains(str)
        End Function

        Public Function StrContainsResponse(ByVal str As String) As Boolean
            Dim result As Boolean = False
            For Each tempStr As String In responses
                If str.Contains(tempStr) Then
                    result = True
                    Exit For
                End If
            Next
            Return result
        End Function

        Public Sub AddResponse(ByVal s1 As String)
            responses.Add(s1)
        End Sub
    End Class
    Public Class AXNeuroSama
        Private nyaa As New Responder(" heart", " heart", " wink", " heart heart heart")
        Private rnd As New DrawRnd()
        Private rate As Integer

        Public Sub New(ByVal rate As Integer)
            ' The higher the rate, the less likely to decorate outputs
            Me.rate = rate
        End Sub

        Public Function Decorate(ByVal output As String) As String
            If output = "" Then
                Return output
            End If
            If rnd.GetSimpleRNDNum(rate) = 0 Then
                Return output & nyaa.GetAResponse()
            End If
            Return output
        End Function
    End Class
    Public Class PercentDripper
        Private dr As New DrawRnd()
        Private limis As Integer = 35

        Public Sub setLimis(ByVal limis As Integer)
            Me.limis = limis
        End Sub

        Public Function drip() As Boolean
            Return dr.GetSimpleRNDNum(100) < limis
        End Function

        Public Function dripPlus(ByVal plus As Integer) As Boolean
            Return dr.GetSimpleRNDNum(100) < limis + plus
        End Function
    End Class
    Public Class AXNPC
        Public responder As New RefreshQ()
        Public dripper As New PercentDripper()
        Public cmdBreaker As New AXCmdBreaker("say")

        Public Sub New(replyStockLim As Integer, outputChance As Integer)
            responder.Limit = replyStockLim
            If outputChance > 0 AndAlso outputChance < 101 Then
                dripper.setLimis(outputChance)
            End If
        End Sub

        Public Function respond() As String
            If dripper.drip() Then
                Return responder.GetRNDElement()
            End If
            Return ""
        End Function

        Public Function respondPlus(plus As Integer) As String
            ' Increase rate of output
            If dripper.dripPlus(plus) Then
                Return responder.GetRNDElement()
            End If
            Return ""
        End Function

        Public Function learn(ear As String) As Boolean
            ' Say hello there: hello there is learned
            Dim temp As String = cmdBreaker.ExtractCmdParam(ear)
            If temp = "" Then
                Return False
            End If
            responder.Add(temp)
            Return True
        End Function

        Public Function strRespond(ear As String) As String
            ' Respond if ear contains a learned input
            If ear = "" Then
                Return ""
            End If
            If dripper.drip() AndAlso responder.StrContainsResponse(ear) Then
                Return responder.GetRNDElement()
            End If
            Return ""
        End Function

        Public Function forceRespond() As String
            Return responder.GetRNDElement()
        End Function

        Public Sub setConjuration(conjuration As String)
            cmdBreaker.conjuration = conjuration
        End Sub
    End Class
    Public Class AXNPC2
        Inherits AXNPC

        Public annoyedQue As New AnnoyedQue(5)

        Public Sub New(replyStockLim As Integer, outputChance As Integer)
            MyBase.New(replyStockLim, outputChance)
        End Sub

        Public Sub strLearn(ear As String)
            ' Learns inputs containing strings that are repeatedly used by others
            annoyedQue.Learn(ear)
            If annoyedQue.IsAnnoyed(ear) Then
                Me.responder.Add(ear)
            End If
        End Sub
    End Class
    Public Class AXPassword
        ' code # to open the gate
        'while gate Is open, code can be changed with: code new_number
        Private isOpen As Boolean = False
        Private maxAttempts As Integer = 3
        Private loginAttempts As Integer = maxAttempts
        Private regexUtil As New RegexUtil()
        Private code As Integer = 0
        Public Function codeUpdate(ear As String) As Boolean
            ' while the gate is toggled on, the password code can be changed
            If Not isOpen Then
                Return False
            End If
            If ear.Contains("code") Then
                Dim temp As String = regexUtil.ExtractRegex(enumRegexGrimoire.int, ear)
                If Not temp = "" Then
                    code = Integer.Parse(temp)
                    Return True
                End If
            End If
            Return False
        End Function

        Public Sub openGate(ear As String)
            If ear.Contains("code") AndAlso (loginAttempts > 0) Then
                Dim noCode As String = regexUtil.ExtractRegex(enumRegexGrimoire.int, ear)
                If noCode = "" Then
                    Return
                End If
                Dim tempCode As Integer = Integer.Parse(noCode)
                If tempCode = code Then
                    loginAttempts = maxAttempts
                    isOpen = True
                Else
                    loginAttempts -= 1
                End If
            End If
        End Sub

        Public Function isGateOpen() As Boolean
            Return isOpen
        End Function

        Public Sub resetAttempts()
            ' should happen once a day or hour to prevent hacking
            loginAttempts = maxAttempts
        End Sub

        Public Function getLoginAttempts() As Integer
            ' return remaining login attempts
            Return loginAttempts
        End Function

        Public Sub closeGate()
            isOpen = False
        End Sub

        Public Sub closeGate(ear As String)
            If ear.Contains("close") Then
                isOpen = False
            End If
        End Sub

        Public Sub setMaxAttempts(maxAttempts As Integer)
            Me.maxAttempts = maxAttempts
        End Sub

        Public Function getCode() As Integer
            If isOpen Then
                Return code
            End If
            Return -1
        End Function

        Public Sub randomizeCode(lim As Integer, minimumLim As Integer)
            code = New DrawRnd().GetSimpleRNDNum(lim) + minimumLim
        End Sub

        Public Function getCodeEvent() As Integer
            ' event feature
            ' get the code during weekly/monthly event after it has been randomized
            Return code
        End Function
    End Class
    Public Class Prompt
        Private regexUtil As New RegexUtil()
        Public kv As New AXKeyValuePair()
        Private prompt As String = ""
        Private regex As String = ""

        Public Sub New()
            kv.SetKey("default")
        End Sub

        Public Function getPrompt() As String
            Return prompt
        End Function

        Public Sub setPrompt(prompt As String)
            Me.prompt = prompt
        End Sub

        Public Function process(in1 As String) As Boolean
            kv.SetValue(regexUtil.ExtractRegex(regex, in1))
            Return kv.GetValue() = "" ' Is prompt still active?
        End Function

        Public Function getKv() As AXKeyValuePair
            Return kv
        End Function

        Public Sub setRegex(regex As String)
            Me.regex = regex
        End Sub
    End Class
    Public Class AXPrompt
        Private isActive As Boolean = False
        Private index As Integer = 0
        Private prompts As New List(Of Prompt)()
        Private kv As AXKeyValuePair = Nothing

        Public Sub addPrompt(p1 As Prompt)
            prompts.Add(p1)
        End Sub

        Public Function getPrompt() As String
            If prompts.Count = 0 Then
                Return ""
            End If
            Return prompts(index).getPrompt()
        End Function

        Public Sub process(in1 As String)
            If prompts.Count = 0 OrElse Not isActive Then
                Return
            End If
            Dim b1 As Boolean = prompts(index).process(in1)
            If Not b1 Then
                kv = prompts(index).getKv()
                index += 1
            End If
            If index = prompts.Count Then
                isActive = False
            End If
        End Sub

        Public Function getActive() As Boolean
            Return isActive
        End Function

        Public Function getKv() As AXKeyValuePair
            If kv Is Nothing Then
                Return Nothing
            End If
            Dim temp As New AXKeyValuePair()
            temp.SetKey(kv.GetKey())
            temp.SetValue(kv.GetValue())
            kv = Nothing
            Return temp
        End Function

        Public Sub activate()
            ' Reset
            isActive = True
            index = 0
        End Sub

        Public Sub deactivate()
            ' Reset
            isActive = False
            index = 0
        End Sub
    End Class
    Public Class AXShoutOut
        Private isActive As Boolean = False
        Public handshake As New Responder()

        Public Sub activate()
            ' Make engage-able
            isActive = True
        End Sub

        Public Function engage(ear As String) As Boolean
            If ear = "" Then
                Return False
            End If
            If isActive Then
                If handshake.StrContainsResponse(ear) Then
                    isActive = False
                    Return True ' Shout out was replied!
                End If
            End If
            ' Unrelated reply to shout out, shout out context is outdated
            isActive = False
            Return False
        End Function
    End Class
    Public Class Strategy
        Private allStrategies As UniqueResponder
        Private strategiesLim As Integer
        Private activeStrategy As UniqueItemSizeLimitedPriorityQueue

        ' Constructor
        Public Sub New(allStrategies As UniqueResponder, strategiesLim As Integer)
            Me.allStrategies = allStrategies
            Me.strategiesLim = strategiesLim
            Me.activeStrategy = New UniqueItemSizeLimitedPriorityQueue()
            Me.activeStrategy.Limit = strategiesLim
            For i As Integer = 0 To Me.strategiesLim - 1
                Me.activeStrategy.Add(Me.allStrategies.GetAResponse())
            Next
        End Sub

        ' Evolve strategies
        Public Sub EvolveStrategies()
            For i As Integer = 0 To Me.strategiesLim - 1
                Me.activeStrategy.Add(Me.allStrategies.GetAResponse())
            Next
        End Sub

        ' Get strategy
        Public Function GetStrategy() As String
            Return Me.activeStrategy.GetRNDElement()
        End Function
    End Class


    Public Class AXStringSplit
        ' May be used to prepare data before saving or after loading.
        ' The advantage is fewer data fields. For example: {skills: s1_s2_s3}
        Private spChar As String = "_"

        Public Sub setSpChar(spChar As String)
            Me.spChar = spChar
        End Sub

        Public Function split(s1 As String) As String()
            Return s1.Split(spChar)
        End Function

        Public Function stringBuilder(sArr As String()) As String
            Dim sb As New StringBuilder()
            For i As Integer = 0 To sArr.Length - 2
                sb.Append(sArr(i))
                sb.Append(Me.spChar)
            Next
            sb.Append(sArr(sArr.Length - 1))
            Return sb.ToString()
        End Function
    End Class
    Public Class AXStrOrDefault
        Public Shared Function getOrDefault(str1 As String, default1 As String) As String
            Return If(String.IsNullOrEmpty(str1), default1, str1)
        End Function
    End Class
    Public Class TrgTime
        Private t As String = "null"
        Private regexUtil As New RegexUtil()
        Private alarm As Boolean = True

        Public Sub setTime(v1 As String)
            t = regexUtil.ExtractRegex(enumRegexGrimoire.simpleTimeStamp, v1)
        End Sub

        Public Function checkAlarm() As Boolean
            Dim now As String = TimeUtils.getCurrentTimeStamp()
            If alarm Then
                If now = t Then
                    alarm = False
                    Return True
                End If
            End If
            If now <> t Then
                alarm = True
            End If
            Return False
        End Function
    End Class
    Public Class Cron
        Inherits TrGEV3
        ' triggers true, limit times, after initial time, and every minutes interval
        ' counter resets at initial time, assuming trigger method was run
        Private minutes As Integer ' Minute interval between triggerings
        Private trgTime As TrgTime
        Private timeStamp As String = ""
        Private initialTimeStamp As String = ""
        Private limit As Integer
        Private counter As Integer = 0

        Public Sub New(ByVal startTime As String, ByVal minutes As Integer, ByVal limit As Integer)
            Me.minutes = minutes
            Me.timeStamp = startTime
            Me.initialTimeStamp = startTime
            trgTime = New TrgTime()
            trgTime.setTime(startTime)
            Me.limit = limit
            If limit < 0 Then
                Me.limit = 1
            End If
        End Sub

        Public Function GetLimit() As Integer
            Return limit
        End Function

        Public Sub SetLimit(ByVal limit As Integer)
            If limit > -1 Then
                Me.limit = limit
            End If
        End Sub

        Public Function GetCounter() As Integer
            Return counter
        End Function

        Public Sub SetMinutes(ByVal minutes As Integer)
            If minutes > -1 Then
                Me.minutes = minutes
            End If
        End Sub

        Public Overloads Function Trigger() As Boolean
            ' delete counter = 0 if you don't want the trigger to work the next day
            If counter = limit Then
                trgTime.setTime(initialTimeStamp)
                counter = 0
                Return False
            End If
            If trgTime.checkAlarm Then
                timeStamp = TimeUtils.getFutureInXMin(minutes)
                trgTime.setTime(timeStamp)
                counter += 1
                Return True
            End If
            Return False
        End Function

        Public Function TriggerWithoutRenewal() As Boolean
            ' delete counter = 0 if you don't want the trigger to work the next day
            If counter = limit Then
                trgTime.setTime(initialTimeStamp)
                Return False
            End If
            If trgTime.checkAlarm Then
                timeStamp = TimeUtils.getFutureInXMin(minutes)
                trgTime.setTime(timeStamp)
                counter += 1
                Return True
            End If
            Return False
        End Function

        Public Overloads Sub Reset()
            ' manual trigger reset
            counter = 0
        End Sub

        Public Sub SetStartTime(ByVal t1 As String)
            initialTimeStamp = t1
            timeStamp = t1
            trgTime.setTime(t1)
            counter = 0
        End Sub

        Public Sub TurnOff()
            counter = limit
        End Sub
    End Class
    Public Class Cycler
        Private cycler As Integer = 0
        Private limit As Integer

        Public Sub New(ByVal limit As Integer)
            Me.limit = limit
            cycler = limit
        End Sub

        Public Function GetLimit() As Integer
            Return limit
        End Function

        Public Sub SetLimit(ByVal limit As Integer)
            Me.limit = limit
        End Sub

        Public Function CycleCount() As Integer
            cycler -= 1
            If cycler < 0 Then
                cycler = limit
            End If
            Return cycler
        End Function

        Public Sub Reset()
            cycler = limit
        End Sub

        Public Sub SetToZero()
            cycler = 0
        End Sub

        Public Sub Sync(ByVal n As Integer)
            If n < -1 OrElse n > limit Then
                Return
            End If
            cycler = n
        End Sub

        Public Function GetMode() As Integer
            Return cycler
        End Function
    End Class
    Public Class Differ
        Private powerLevel As Integer = 90
        Private difference As Integer = 0

        Public Function GetPowerLevel() As Integer
            Return powerLevel
        End Function

        Public Function GetDifference() As Integer
            Return difference
        End Function

        Public Sub ClearPowerLVDifference()
            difference = 0
        End Sub

        Public Sub SamplePowerLV(ByVal pl As Integer)
            ' pl is the current power level
            difference = pl - powerLevel
            powerLevel = pl
        End Sub
    End Class
    Public Class Eliza
        Private Shared reflections As New Dictionary(Of String, String)() From {
        {"am", "are"},
        {"was", "were"},
        {"i", "you"},
        {"i'd", "you would"},
        {"i've", "you have"},
        {"my", "your"},
        {"are", "am"},
        {"you've", "I have"},
        {"you'll", "I will"},
        {"your", "my"},
        {"yours", "mine"},
        {"you", "i"},
        {"me", "you"}
    }

        Public Class PhraseMatcher
            Private matcher As Regex
            Private responses As List(Of String)
            Public context As String = ""
            Public param As String = ""
            Public infoRequest As String = ""

            Public Sub New(ByVal matcher As String, ByVal responses As List(Of String))
                Me.matcher = New Regex(matcher)
                Me.responses = responses
            End Sub

            Public Function Matches(ByVal s As String) As Boolean
                Return Me.matcher.IsMatch(s)
            End Function

            Public Function Respond(ByVal s As String) As String
                Dim m As Match = Me.matcher.Match(s)
                Me.context = Me.matcher.ToString() ' context
                Dim p As String = Me.RandomPhrase()
                For i As Integer = 0 To m.Groups.Count - 2
                    Dim groupValue As String = Reflect(m.Groups(i + 1).Value)
                    Me.param = groupValue ' param
                    Me.infoRequest = p ' more info request
                    p = p.Replace("{" & i & "}", groupValue)
                Next
                Return p
            End Function

            Public Shared Function Reflect(ByVal s As String) As String
                Dim words As String() = s.Split(" ")
                For i As Integer = 0 To words.Length - 1
                    If Eliza.reflections.ContainsKey(words(i)) Then
                        words(i) = Eliza.reflections(words(i))
                    End If
                Next
                Return String.Join(" ", words)
            End Function

            Public Function RandomPhrase() As String
                Dim rand As New Random()
                Dim randomIndex As Integer = Math.Abs(rand.Next(0, Me.responses.Count))
                Return Me.responses(randomIndex)
            End Function

            Public Overrides Function ToString() As String
                Return Me.matcher.ToString() & ":" & String.Join(", ", Me.responses)
            End Function
        End Class

        Public babble As New List(Of PhraseMatcher)() From {
        New PhraseMatcher("i need (.*)", New List(Of String)() From {
            "Why do you need {0}?",
            "Would it really help you to get {0}?",
            "Are you sure you need {0}?"
        })
    }

        Public Function Respond(ByVal msg As String) As String
            For Each pm As PhraseMatcher In Me.babble
                If pm.Matches(msg) Then
                    Return pm.Respond(msg.ToLower())
                End If
            Next
            Return ""
        End Function
    End Class
    Public Class InputFilter
        ' Filter out non-relevant input or filter in relevant data

        Public Function Filter(ByVal ear As String, ByVal skin As String, ByVal eye As String) As String
            ' Override me
            Return ""
        End Function

        Public Function Filter(ByVal ear As String) As AXKeyValuePair
            ' Override me: key = context/category, value: param
            Return New AXKeyValuePair()
        End Function
    End Class
    Public Class Magic8Ball
        Private questions As Responder = New Responder()
        Private answers As Responder = New Responder()

        Public Sub SetQuestions(questions As Responder)
            Me.questions = questions
        End Sub

        Public Sub SetAnswers(answers As Responder)
            Me.answers = answers
        End Sub

        Public Function GetQuestions() As Responder
            Return questions
        End Function

        Public Function GetAnswers() As Responder
            Return answers
        End Function

        Public Sub New()
            ' Answers
            answers.AddResponse("It is certain")
            answers.AddResponse("It is decidedly so")
            answers.AddResponse("Without a doubt")
            answers.AddResponse("Yes definitely")
            answers.AddResponse("You may rely on it")
            answers.AddResponse("As I see it, yes")
            answers.AddResponse("Most likely")
            answers.AddResponse("Outlook good")
            answers.AddResponse("Yes")
            answers.AddResponse("Signs point to yes")
            answers.AddResponse("Reply hazy, try again")
            answers.AddResponse("Ask again later")
            answers.AddResponse("Better not tell you now")
            answers.AddResponse("Cannot predict now")
            answers.AddResponse("Concentrate and ask again")
            answers.AddResponse("Don’t count on it")
            answers.AddResponse("My reply is no")
            answers.AddResponse("My sources say no")
            answers.AddResponse("Outlook not so good")
            answers.AddResponse("Very doubtful")

            ' Questions
            questions = New Responder("will i", "can i expect", "should i", "may i", "is it a good idea", "will it be a good idea for me to", "is it possible", "future hold", "will there be")
        End Sub

        Public Function Engage(ear As String) As Boolean
            If String.IsNullOrEmpty(ear) Then
                Return False
            End If
            If questions.StrContainsResponse(ear) Then
                Return True
            End If
            Return False
        End Function

        Public Function Reply() As String
            Return answers.GetAResponse()
        End Function
    End Class
    ' Define the OnOffSwitch class
    Class OnOffSwitch
        Private mode As Boolean
        Private timeGate As TimeGate
        Private turnOn As Responder
        Private off As Responder

        Public Sub New()
            mode = False
            timeGate = New TimeGate(5)
            turnOn = New Responder("on", "talk to me")
            off = New Responder("off", "stop", "shut up", "shut it", "whatever", "whateva")
        End Sub

        Public Sub SetPause(minutes As Integer)
            Me.timeGate.SetPause(minutes)
        End Sub

        Public Sub SetOn(onResponder As Responder)
            Me.turnOn = onResponder
        End Sub

        Public Sub SetOff(offResponder As Responder)
            Me.off = offResponder
        End Sub

        Public Function GetMode(ear As String) As Boolean
            If Me.turnOn.ResponsesContainsStr(ear) Then
                Me.timeGate.OpenGate()
                Me.mode = True
                Return True
            ElseIf Me.off.ResponsesContainsStr(ear) Then
                Me.timeGate.Close()
                Me.mode = False
            End If
            If Me.timeGate.IsClosed() Then
                Me.mode = False
            End If
            Return Me.mode
        End Function
    End Class
    Public Class RailChatBot
        Private dic As New Dictionary(Of String, RefreshQ)()
        Private context As String = "default"

        Public Sub New()
            dic.Add(context, New RefreshQ())
        End Sub

        Public Sub SetContext(context As String)
            If Not String.IsNullOrEmpty(context) Then
                Me.context = context
            End If
        End Sub

        Public Function RespondMonolog(ear As String) As String
            ' Monolog mode
            ' Recommended use of filter for the output results
            If String.IsNullOrEmpty(ear) Then
                Return ""
            End If
            If Not dic.ContainsKey(ear) Then
                dic.Add(ear, New RefreshQ())
            End If
            Dim temp As String = dic(ear).GetRNDElement()
            If Not String.IsNullOrEmpty(temp) Then
                context = temp
            End If
            Return temp
        End Function

        Public Sub Learn(ear As String)
            ' Use per each think cycle
            If String.IsNullOrEmpty(ear) Then
                Return
            End If
            If Not dic.ContainsKey(ear) Then
                dic.Add(ear, New RefreshQ())
                dic(context).Add(ear)
                context = ear
                Return
            End If
            dic(context).Add(ear)
            context = ear
        End Sub

        Public Sub LearnKeyValue(context As String, reply As String)
            ' Learn questions and answers/key values
            If Not dic.ContainsKey(context) Then
                dic.Add(context, New RefreshQ())
            End If
            If Not dic.ContainsKey(reply) Then
                dic.Add(reply, New RefreshQ())
            End If
            dic(context).Add(reply)
        End Sub

        Public Function Monolog() As String
            ' Succession of outputs without input involved
            Return RespondMonolog(context)
        End Function

        Public Function RespondDialog(ear As String) As String
            ' Dialog mode
            ' Recommended use of filter for the output results
            If String.IsNullOrEmpty(ear) Then
                Return ""
            End If
            If Not dic.ContainsKey(ear) Then
                dic.Add(ear, New RefreshQ())
            End If
            Dim temp As String = dic(ear).GetRNDElement()
            Return temp
        End Function
        Public Sub FeedKeyValuePairs(ByVal kvList As List(Of AXKeyValuePair))
            If kvList.Count = 0 Then
                Return
            End If
            For Each kv As AXKeyValuePair In kvList
                LearnKeyValue(kv.GetKey, kv.GetValue)
            Next
        End Sub
        'Public Sub LearnV2(ByVal ear As String, ByVal eliza_deducer As ElizaDeducer)
        '    FeedKeyValuePairs(eliza_deducer.Respond(ear))
        '    Learn(ear)
        'End Sub
    End Class
    Public Class SkillHubAlgDispenser
        ' Super class to output an algorithm out of a selection of skills
        ' Engage the hub with dispenseAlg and return the value to outAlg attribute
        ' of the containing skill (which houses the skill hub)
        ' This module enables using a selection of 1 skill for triggers instead of having the triggers engage on multiple skills
        ' The method is ideal for learnability and behavioral modifications
        ' Use a learnability auxiliary module as a condition to run an active skill shuffle or change method
        ' (rndAlg, cycleAlg)
        ' Moods can be used for specific cases to change behavior of the AGI, for example low energy state
        ' For that use (moodAlg)

        Private ReadOnly skills As New List(Of Skill)()
        Private activeSkill As Integer = 0
        Private ReadOnly tempN As New Neuron()
        Private ReadOnly rand As New Random()
        Private kokoro As New Kokoro(New AbsDictionaryDB())

        Public Sub New(ParamArray skillsParams As Skill())
            For Each skill As Skill In skillsParams
                skill.SetKokoro(Me.kokoro)
                skills.Add(skill)
            Next
        End Sub

        Public Sub setKokoro(kokoro As Kokoro)
            Me.kokoro = kokoro
            For Each skill As Skill In skills
                skill.SetKokoro(Me.kokoro)
            Next
        End Sub

        Public Function addSkill(skill As Skill) As SkillHubAlgDispenser
            ' Builder pattern
            skill.SetKokoro(Me.kokoro)
            skills.Add(skill)
            Return Me
        End Function

        Public Function dispenseAlgorithm(ear As String, skin As String, eye As String) As AlgorithmV2
            ' Return value to outAlg param of (external) summoner DiskillV2
            skills(activeSkill).Input(ear, skin, eye)
            skills(activeSkill).Output(tempN)
            For i As Integer = 1 To 5
                Dim temp As Algorithm = tempN.GetAlg(i)
                If temp IsNot Nothing Then
                    Return New AlgorithmV2(i, temp)
                End If
            Next
            Return Nothing
        End Function

        Public Sub randomizeActiveSkill()
            activeSkill = rand.Next(skills.Count)
        End Sub

        Public Sub setActiveSkillWithMood(mood As Integer)
            ' Mood integer represents active skill
            ' Different mood = different behavior
            If mood > -1 AndAlso mood < skills.Count Then
                activeSkill = mood
            End If
        End Sub

        Public Sub cycleActiveSkill()
            ' Changes active skill
            ' I recommend this method be triggered with a Learnability or SpiderSense object
            activeSkill += 1
            If activeSkill = skills.Count Then
                activeSkill = 0
            End If
        End Sub

        Public Function getSize() As Integer
            Return skills.Count
        End Function
        Public Function ActiveSkillRef() As Skill
            Return Me.skills(Me.activeSkill)
        End Function

    End Class
    Public Class SpiderSense
        ' Enables event prediction
        Private spiderSense As Boolean = False
        Private events As New UniqueItemSizeLimitedPriorityQueue()
        Private alerts As New UniqueItemSizeLimitedPriorityQueue()
        Private prev As String = ""

        Public Function AddEvent([event] As String) As SpiderSense
            ' Builder pattern
            events.Add([event])
            Return Me
        End Function

        ' Input param can be run through an input filter prior to this function
        ' Weather-related data (sky state) only for example for weather events predictions
        Public Sub Learn(in1 As String)
            ' Simple prediction of an event from the events queue:
            If alerts.Contains(in1) Then
                spiderSense = True
                Return
            End If
            ' Event has occurred, remember what led to it
            If events.Contains(in1) Then
                alerts.Add(prev)
                Return
            End If
            ' Nothing happened
            prev = in1
        End Sub

        Public Function GetSpiderSense() As Boolean
            ' Spider sense is tingling? Event predicted?
            Dim temp As Boolean = spiderSense
            spiderSense = False
            Return temp
        End Function

        Public Function GetAlertsShallowCopy() As List(Of String)
            ' Return shallow copy of alerts list
            Return alerts.getElements()
        End Function

        Public Function GetAlertsClone() As List(Of String)
            ' Return deep copy of alerts list
            Dim dc As New DeepCopier()
            Return dc.DeepCopyStringList(alerts.getElements())
        End Function

        Public Sub ClearAlerts()
            ' This can, for example, prevent war because, say, once a month or a year you stop
            ' being on alert against a rival
            alerts.Clear()
        End Sub
        Public Function EventTriggered(ByVal in1 As String) As Boolean
            Return events.Contains(in1)
        End Function

        ' Side note:
        ' Use separate spider sense for data learned by hearsay in contrast to actual experience
        ' as well as lies (false predictions)
    End Class
    Public Class TimeAccumulator
        ' Accumulator ++ each tick minutes interval
        Private timeGate As New TimeGate(5)
        Private accumulator As Integer = 0

        Public Sub SetTick(tick As Integer)
            timeGate.SetPause(tick)
        End Sub

        Public Sub New(tick As Integer)
            ' Accumulation ticker
            Me.timeGate = New TimeGate(tick)
            timeGate.OpenGate()
        End Sub

        Public Function GetAccumulator() As Integer
            Return accumulator
        End Function

        Public Sub Tick()
            If timeGate.IsClosed() Then
                timeGate.OpenGate()
                accumulator += 1
            End If
        End Sub

        Public Sub Reset()
            accumulator = 0
        End Sub

        Public Sub DecAccumulator()
            If accumulator > 0 Then
                accumulator -= 1
            End If
        End Sub
    End Class
    Public Class TODOListManager
        ' Manages to-do tasks.
        ' q1 tasks are mentioned once and forgotten.
        ' backup tasks are the memory of recently mentioned tasks.
        Private q1 As New UniqueItemSizeLimitedPriorityQueue()
        Private backup As New UniqueItemSizeLimitedPriorityQueue()

        Public Sub New(todoLim As Integer)
            q1.Limit = todoLim
            backup.Limit = todoLim
        End Sub

        Public Sub AddTask(e1 As String)
            q1.Add(e1)
        End Sub

        Public Function GetTask() As String
            Dim temp As String = q1.Poll()
            If Not temp = "" Then
                backup.Add(temp)
            End If
            Return temp
        End Function

        Public Function GetOldTask() As String
            ' Task graveyard (tasks you've tackled already)
            Return backup.GetRNDElement()
        End Function

        Public Sub ClearAllTasks()
            q1.Clear()
            backup.Clear()
        End Sub

        Public Sub ClearTask(task As String)
            q1.RemoveItem(task)
            backup.RemoveItem(task)
        End Sub

        Public Function ContainsTask(task As String) As Boolean
            Return backup.Contains(task)
        End Function
    End Class
    Public Class TrgArgue
        Public commands As New UniqueItemSizeLimitedPriorityQueue()
        Public contextCommands As New UniqueItemSizeLimitedPriorityQueue()
        Private trgTolerance As Boolean = False
        Private counter As Integer = 0 ' Count argues/requests made in succession
        ' (Breaking point of argument can be established (argue till counter == N))

        Public Function GetCounter() As Integer
            Return counter
        End Function

        Public Function EngageCommand(s1 As String) As Integer
            ' 0 -> no engagement
            ' 1 -> engaged boolean gate (request made)
            ' 2 -> engaged argument: consecutive request made (request in succession after a previous request)
            If String.IsNullOrEmpty(s1) Then
                Return 0
            End If
            If contextCommands.Contains(s1) Then
                If trgTolerance Then
                    counter += 1
                End If
                trgTolerance = True
                Return 1
            End If
            If trgTolerance Then
                If Not commands.StrContainsResponse(s1) Then
                    trgTolerance = False
                    counter = 0
                    Return 0
                Else
                    counter += 1
                    Return 2
                End If
            End If
            Return 0
        End Function

        Public Sub Disable()
            ' Context commands are disabled until the next engagement with a command
            trgTolerance = False
        End Sub
    End Class
    Public Class TrgEveryNMinutes
        Inherits TrGEV3
        ' Trigger returns true every minutes interval, post start time
        Private minutes As Integer ' Minute interval between triggerings
        Private trgTime As TrgTime
        Private timeStamp As String = ""

        Public Sub New(startTime As String, minutes As Integer)
            Me.minutes = minutes
            Me.timeStamp = startTime
            trgTime = New TrgTime()
            trgTime.setTime(startTime)
        End Sub

        Public Sub SetMinutes(minutes As Integer)
            If minutes > -1 Then
                Me.minutes = minutes
            End If
        End Sub

        Public Overloads Function Trigger() As Boolean
            If trgTime.checkAlarm Then
                timeStamp = TimeUtils.getFutureInXMin(minutes)
                trgTime.setTime(timeStamp)
                Return True
            End If
            Return False
        End Function

        Public Overloads Sub Reset()
            timeStamp = TimeUtils.getCurrentTimeStamp()
        End Sub
    End Class
    Public Class TrgMinute
        Inherits TrGEV3
        ' Trigger returns true at minute once per hour
        Private hour1 As Integer = -1
        Private minute As Integer
        Private rand As New Random()

        Public Sub New()
            minute = rand.Next(60)
        End Sub

        Public Sub New(minute As Integer)
            Me.minute = minute
        End Sub

        Public Overloads Function Trigger() As Boolean
            Dim tempHour As Integer = TimeUtils.getHoursAsInt()
            If tempHour <> hour1 Then
                If TimeUtils.getMinutesAsInt() = minute Then
                    hour1 = tempHour
                    Return True
                End If
            End If
            Return False
        End Function

        Public Overloads Sub Reset()
            hour1 = -1
        End Sub
    End Class
    Public Class TrgSnooze
        Inherits TrGEV3
        ' This boolean gate will return true per minute interval
        ' max repeats times.
        Private repeats As Integer = 0
        Private maxrepeats As Integer ' 2 recommended
        Private snooze As Boolean = True
        Private snoozeInterval As Integer = 5

        Public Sub New(maxrepeats As Integer)
            Me.maxrepeats = maxrepeats
        End Sub

        Public Sub SetSnoozeInterval(snoozeInterval As Integer)
            If snoozeInterval > 1 AndAlso snoozeInterval < 11 Then
                Me.snoozeInterval = snoozeInterval
            End If
        End Sub

        Public Sub SetMaxrepeats(maxrepeats As Integer)
            Me.maxrepeats = maxrepeats
            Reset()
        End Sub

        Public Overloads Sub Reset()
            ' Refill trigger
            ' Engage this code when an alarm clock was engaged to enable snoozing
            repeats = maxrepeats
        End Sub

        Public Overloads Function Trigger() As Boolean
            ' Trigger a snooze alarm?
            Dim minutes As Integer = TimeUtils.getMinutesAsInt()
            If minutes Mod snoozeInterval <> 0 Then
                snooze = True
                Return False
            End If
            If repeats > 0 AndAlso snooze Then
                snooze = False
                repeats -= 1
                Return True
            End If
            Return False
        End Function

        Public Sub Disable()
            ' Engage this method to stop the snoozing
            repeats = 0
        End Sub
    End Class
    Public Class AXContextCmd
        ' Engage on commands
        ' When commands are engaged, context commands can also engage
        Public commands As New UniqueItemSizeLimitedPriorityQueue()
        Public contextCommands As New UniqueItemSizeLimitedPriorityQueue()
        Public trgTolerance As Boolean = False

        Public Function EngageCommand(s1 As String) As Boolean
            If String.IsNullOrEmpty(s1) Then
                Return False
            End If
            If contextCommands.Contains(s1) Then
                trgTolerance = True
                Return True
            End If
            If trgTolerance AndAlso Not commands.Contains(s1) Then
                trgTolerance = False
                Return False
            End If
            Return trgTolerance
        End Function

        Public Sub Disable()
            ' Context commands are disabled till next engagement with a command
            trgTolerance = False
        End Sub
    End Class
    Public Class AXConvince
        Private req As AXContextCmd
        Private reset As New Responder("reset")
        Private min As Integer = 3 ' Minimum requests till agreement
        Private max As Integer = 6
        Private rnd As New DrawRnd()
        Private counter As Integer = 0
        Private mode As Boolean = False

        Public Sub New(req As AXContextCmd)
            Me.req = req
        End Sub

        Public Function Engage(ear As String) As Integer
            ' 0: nothing, 1: no, 2: yes, 3: just been reset to no again
            If reset.ResponsesContainsStr(ear) Then
                counter = 0
                mode = False
                min += rnd.GetSimpleRNDNum(max)
                Return 3
            End If
            If req.EngageCommand(ear) Then
                counter += 1
                If counter < min Then
                    Return 1
                Else
                    mode = True
                    Return 2 ' Convinced
                End If
            End If
            Return 0
        End Function

        Public Function IsConvinced() As Boolean
            Return mode
        End Function
    End Class
    Class ChangeDetector
        Private A As String
        Private B As String
        Private prev As Integer = -1

        Public Sub New(ByVal a As String, ByVal b As String)
            Me.A = a
            Me.B = b
        End Sub

        Public Function DetectChange(ByVal ear As String) As Integer
            ' a->b return 2; b->a return 1; else return 0
            If String.IsNullOrEmpty(ear) Then
                Return 0
            End If

            Dim current As Integer = -1
            If ear.Contains(A) Then
                current = 1
            ElseIf ear.Contains(B) Then
                current = 2
            Else
                Return 0
            End If

            Dim result As Integer = 0
            If current = 1 AndAlso prev = 2 Then
                result = 1
            End If
            If current = 2 AndAlso prev = 1 Then
                result = 2
            End If

            prev = current
            Return result
        End Function
    End Class
    Public Class Excluder
        Private ReadOnly startsWith As New List(Of String)()
        Private ReadOnly endsWith As New List(Of String)()

        Public Sub AddStartsWith(s1 As String)
            If Not startsWith.Contains("^(" & s1 & ").*") Then
                startsWith.Add("^(" & s1 & ").*")
            End If
        End Sub

        Public Sub AddEndsWith(s1 As String)
            If Not endsWith.Contains("(.*)(?=" & s1 & ")") Then
                endsWith.Add("(.*)(?=" & s1 & ")")
            End If
        End Sub

        Public Function Exclude(ear As String) As Boolean
            Dim r1 As New RegexUtil() ' Assuming RegexUtil is defined elsewhere
            For Each tempStr As String In startsWith
                If r1.ExtractRegex(tempStr, ear).Length > 0 Then
                    Return True
                End If
            Next
            For Each tempStr As String In endsWith
                If r1.ExtractRegex(tempStr, ear).Length > 0 Then
                    Return True
                End If
            Next
            Return False
        End Function
    End Class
    Public Class TimedMessages
        Public Property Messages As New Dictionary(Of String, String)()
        Private lastMSG As String = "nothing"
        Private msg As Boolean = False

        Public Sub AddMSG(ear As String)
            Dim ru1 As New RegexUtil()
            Dim tempMSG As String = ru1.ExtractRegex("(?<=remind me to).*?(?=at)", ear)
            If String.IsNullOrEmpty(tempMSG) Then
                Return
            End If
            Dim timeStamp As String = ru1.ExtractRegex(enumRegexGrimoire.simpleTimeStamp, ear)
            If String.IsNullOrEmpty(timeStamp) Then
                Return
            End If
            Messages(timeStamp) = tempMSG
        End Sub

        Public Sub AddMSGV2(timeStamp As String, msg As String)
            Messages(timeStamp) = msg
        End Sub

        Public Sub SprinkleMSG(msg As String, amount As Integer)
            For i As Integer = 0 To amount - 1
                Messages(GenerateRandomTimestamp()) = msg
            Next
        End Sub

        Public Shared Function GenerateRandomTimestamp() As String
            Dim random As New Random()
            Dim minutes As Integer = random.Next(60)
            Dim m As String = If(minutes > 9, minutes.ToString(), $"0{minutes}")
            Dim hours As Integer = random.Next(12)
            Return If(hours > 9, $"{hours}:{m}", $"0{hours}:{m}")
        End Function

        Public Sub Clear()
            Messages = New Dictionary(Of String, String)()
        End Sub

        Public Sub Tick()
            Dim now As String = TimeUtils.getCurrentTimeStamp()
            If Messages.ContainsKey(now) Then
                If Not lastMSG.Equals(Messages(now)) Then
                    lastMSG = Messages(now)
                    msg = True
                End If
            End If
        End Sub

        Public Function GetLastMSG() As String
            msg = False
            Return lastMSG
        End Function

        Public Function GetMsg() As Boolean
            Return msg
        End Function
    End Class
    Public Class AlgorithmV2
        Private priority As Integer = 4
        Private alg As Algorithm = Nothing

        Public Sub New(ByVal priority As Integer, ByVal alg As Algorithm)
            Me.priority = priority
            Me.alg = alg
        End Sub

        Public Function GetPriority() As Integer
            Return priority
        End Function

        Public Sub SetPriority(ByVal priority As Integer)
            Me.priority = priority
        End Sub

        Public Function GetAlg() As Algorithm
            Return alg
        End Function

        Public Sub SetAlg(ByVal alg As Algorithm)
            Me.alg = alg
        End Sub
    End Class
    Public Class AXSkillBundle
        Private skills As New List(Of Skill)()
        Private tempN As New Neuron()
        Private kokoro As New Kokoro(New AbsDictionaryDB())

        Public Sub SetKokoro(ByVal kokoro As Kokoro)
            Me.kokoro = kokoro
            For Each skill As Skill In skills
                skill.SetKokoro(Me.kokoro)
            Next
        End Sub

        Public Sub New(ParamArray skillsParams As Skill())
            For Each skill As Skill In skillsParams
                skill.SetKokoro(Me.kokoro)
                skills.Add(skill)
            Next
        End Sub

        Public Function AddSkill(ByVal skill As Skill) As AXSkillBundle
            ' Builder pattern
            skill.SetKokoro(Me.kokoro)
            skills.Add(skill)
            Return Me
        End Function

        Public Function DispenseAlgorithm(ByVal ear As String, ByVal skin As String, ByVal eye As String) As AlgorithmV2
            For Each skill As Skill In skills
                skill.Input(ear, skin, eye)
                skill.Output(tempN)
                For j As Integer = 1 To 5
                    Dim temp As Algorithm = tempN.GetAlg(j)
                    If temp IsNot Nothing Then
                        Return New AlgorithmV2(j, temp)
                    End If
                Next
            Next
            Return Nothing
        End Function

        Public Function GetSize() As Integer
            Return skills.Count
        End Function
    End Class
    Public Class UniqueRandomGenerator
        Private numbers As List(Of Integer)
        Private remainingNumbers As List(Of Integer)

        Public Sub New(n1 As Integer)
            numbers = Enumerable.Range(0, n1).ToList()
            remainingNumbers = New List(Of Integer)()
            Reset()
        End Sub

        Public Sub Reset()
            remainingNumbers = New List(Of Integer)(numbers)
            Dim rnd As New Random()
            remainingNumbers = remainingNumbers.OrderBy(Function() rnd.Next()).ToList()
        End Sub

        Public Function GetUniqueRandom() As Integer
            If remainingNumbers.Count = 0 Then
                Reset()
            End If
            Dim index As Integer = remainingNumbers.Count - 1
            Dim value As Integer = remainingNumbers(index)
            remainingNumbers.RemoveAt(index)
            Return value
        End Function
    End Class
    Public Class UniqueResponder
        Private responses As List(Of String)
        Private urg As UniqueRandomGenerator

        ' Constructor
        Public Sub New(ParamArray replies As String())
            responses = New List(Of String)()
            urg = New UniqueRandomGenerator(replies.Length)
            For Each response As String In replies
                responses.Add(response)
            Next
        End Sub

        ' Method to get a response
        Public Function GetAResponse() As String
            If responses.Count = 0 Then
                Return ""
            End If
            Return responses(urg.GetUniqueRandom())
        End Function

        ' Method to check if responses contain a string
        Public Function ResponsesContainsStr(item As String) As Boolean
            Return responses.Contains(item)
        End Function

        ' Method to check if a string contains any response
        Public Function StrContainsResponse(item As String) As Boolean
            For Each response As String In responses
                If response.Length = 0 Then
                    Continue For
                End If
                If item.Contains(response) Then
                    Return True
                End If
            Next
            Return False
        End Function

        ' Method to add a response
        Public Sub AddResponse(s1 As String)
            If Not responses.Contains(s1) Then
                responses.Add(s1)
                urg = New UniqueRandomGenerator(responses.Count)
            End If
        End Sub
    End Class
    Public Class EventChat
        Private dic As Dictionary(Of String, UniqueResponder)

        ' Constructor
        Public Sub New(ur As UniqueResponder, ParamArray args() As String)
            dic = New Dictionary(Of String, UniqueResponder)()
            For Each arg As String In args
                dic(arg) = ur
            Next
        End Sub

        ' Add items
        Public Sub AddItems(ur As UniqueResponder, ParamArray args() As String)
            For Each arg As String In args
                dic(arg) = ur
            Next
        End Sub

        ' Add key-value pair
        Public Sub AddKeyValue(key As String, value As String)
            If dic.ContainsKey(key) Then
                dic(key).AddResponse(value)
            Else
                dic(key) = New UniqueResponder(value)
            End If
        End Sub

        ' Get response
        Public Function Response(in1 As String) As String
            If dic.ContainsKey(in1) Then
                Return dic(in1).GetAResponse()
            Else
                Return ""
            End If
        End Function
    End Class
    Public Class AXStandBy
        Private ReadOnly tg As TimeGate

        Public Sub New(pause As Integer)
            Me.tg = New TimeGate(pause)
            Me.tg.OpenGate()
        End Sub

        Public Function standBy(ear As String) As Boolean
            ' only returns true after pause minutes of no input
            If ear.Length > 0 Then
                ' restart count
                Me.tg.OpenGate()
                Return False
            End If
            If Me.tg.IsClosed() Then
                ' time out without input, stand by is true
                Me.tg.OpenGate()
                Return True
            End If
            Return False
        End Function
    End Class

    Public Class LimUniqueResponder
        Private responses As List(Of String)
        Private urg As UniqueRandomGenerator = New UniqueRandomGenerator(0)
        Private ReadOnly lim As Integer

        ' Constructor
        Public Sub New(lim As Integer)
            responses = New List(Of String)()
            Me.lim = lim
        End Sub

        ' Method to get a response
        Public Function GetAResponse() As String
            If responses.Count = 0 Then
                Return ""
            End If
            Return responses(urg.GetUniqueRandom())
        End Function

        ' Method to check if responses contain a string
        Public Function ResponsesContainsStr(item As String) As Boolean
            Return responses.Contains(item)
        End Function

        ' Method to check if a string contains any response
        Public Function StrContainsResponse(item As String) As Boolean
            For Each response As String In responses
                If String.IsNullOrEmpty(response) Then
                    Continue For
                End If
                If item.Contains(response) Then
                    Return True
                End If
            Next
            Return False
        End Function

        ' Method to add a response
        Public Sub AddResponse(s1 As String)
            If responses.Contains(s1) Then
                responses.Remove(s1)
                responses.Add(s1)
                Return
            End If
            If responses.Count > lim - 1 Then
                responses.RemoveAt(0)
            Else
                urg = New UniqueRandomGenerator(responses.Count + 1)
            End If
            responses.Add(s1)
        End Sub

        ' Method to add multiple responses
        Public Sub AddResponses(ParamArray replies As String())
            For Each value As String In replies
                AddResponse(value)
            Next
        End Sub

        ' Method to get a savable string
        Public Function GetSavableStr() As String
            Return String.Join("_", responses)
        End Function

        ' Method to get the last item
        Public Function GetLastItem() As String
            If responses.Count = 0 Then
                Return ""
            End If
            Return responses(responses.Count - 1)
        End Function
        Public Function Clone() As LimUniqueResponder
            Dim clonedResponder As New LimUniqueResponder(Me.lim)
            clonedResponder.responses = New List(Of String)(Me.responses)
            clonedResponder.urg = New UniqueRandomGenerator(clonedResponder.responses.Count)
            Return clonedResponder
        End Function
    End Class

    Public Class EventChatV2
        Private ReadOnly dic As New Dictionary(Of String, LimUniqueResponder)()
        Private ReadOnly modifiedKeys As New HashSet(Of String)()
        Private ReadOnly lim As Integer

        ' Constructor
        Public Sub New(lim As Integer)
            Me.lim = lim
        End Sub

        ' Get modified keys
        Public Function GetModifiedKeys() As HashSet(Of String)
            Return modifiedKeys
        End Function

        ' Check if a key exists
        Public Function KeyExists(key As String) As Boolean
            Return modifiedKeys.Contains(key)
        End Function

        ' Add items
        Public Sub AddItems(ur As LimUniqueResponder, ParamArray args As String())
            For Each arg As String In args
                dic(arg) = ur.Clone()
            Next
        End Sub

        ' Add from database
        Public Sub AddFromDB(key As String, value As String)
            If String.IsNullOrEmpty(value) OrElse value = "null" Then
                Return
            End If
            Dim tool1 As New AXStringSplit()
            Dim values As String() = tool1.split(value)
            If Not dic.ContainsKey(key) Then
                dic(key) = New LimUniqueResponder(lim)
            End If
            For Each item As String In values
                dic(key).AddResponse(item)
            Next
        End Sub

        ' Add key-value pair
        Public Sub AddKeyValue(key As String, value As String)
            modifiedKeys.Add(key)
            If dic.ContainsKey(key) Then
                dic(key).AddResponse(value)
            Else
                dic(key) = New LimUniqueResponder(lim)
                dic(key).AddResponse(value)
            End If
        End Sub

        ' Add key-values from a list of AXKeyValuePair
        Public Sub AddKeyValues(elizaResults As List(Of AXKeyValuePair))
            For Each pair As AXKeyValuePair In elizaResults
                AddKeyValue(pair.GetKey(), pair.GetValue())
            Next
        End Sub

        ' Get response
        Public Function Response(in1 As String) As String
            Return If(dic.ContainsKey(in1), dic(in1).GetAResponse(), "")
        End Function

        ' Get latest response
        Public Function ResponseLatest(in1 As String) As String
            Return If(dic.ContainsKey(in1), dic(in1).GetLastItem(), "")
        End Function

        ' Get save string
        Public Function GetSaveStr(key As String) As String
            Return dic(key).GetSavableStr()
        End Function
    End Class

    Public Class ElizaDeducer
        ' This class populates a special chat dictionary based on the matches added via its AddPhraseMatcher function.
        ' See subclass ElizaDeducerInitializer for example:
        ' Dim ed As New ElizaDeducerInitializer(2) ' 2 = limit of replies per input

        Public babble2 As List(Of PhraseMatcher)
        Private ReadOnly patternIndex As Dictionary(Of String, List(Of PhraseMatcher))
        Private ReadOnly responseCache As Dictionary(Of String, List(Of AXKeyValuePair))
        Private ReadOnly ec2 As EventChatV2 ' Chat dictionary, use getter for access. Hardcoded replies can also be added.

        Public Sub New(lim As Integer)
            babble2 = New List(Of PhraseMatcher)()
            patternIndex = New Dictionary(Of String, List(Of PhraseMatcher))()
            responseCache = New Dictionary(Of String, List(Of AXKeyValuePair))()
            ec2 = New EventChatV2(lim)
        End Sub

        Public Function GetEc2() As EventChatV2
            Return ec2
        End Function

        Public Sub Learn(msg As String)
            ' Populate EventChat dictionary
            ' Check cache first
            If responseCache.ContainsKey(msg) Then
                ec2.AddKeyValues(New List(Of AXKeyValuePair)(responseCache(msg)))
            End If

            ' Search for matching patterns
            Dim potentialMatchers As List(Of PhraseMatcher) = GetPotentialMatchers(msg)
            For Each pm As PhraseMatcher In potentialMatchers
                If pm.Matches(msg) Then
                    Dim response As List(Of AXKeyValuePair) = pm.Respond(msg)
                    responseCache(msg) = response
                    ec2.AddKeyValues(response)
                End If
            Next
        End Sub

        Public Function LearnedBool(msg As String) As Boolean
            ' Same as Learn method but returns True if it learned new replies
            Dim learned As Boolean = False

            ' Populate EventChat dictionary
            ' Check cache first
            If responseCache.ContainsKey(msg) Then
                ec2.AddKeyValues(New List(Of AXKeyValuePair)(responseCache(msg)))
                learned = True
            End If

            ' Search for matching patterns
            Dim potentialMatchers As List(Of PhraseMatcher) = GetPotentialMatchers(msg)
            For Each pm As PhraseMatcher In potentialMatchers
                If pm.Matches(msg) Then
                    Dim response As List(Of AXKeyValuePair) = pm.Respond(msg)
                    responseCache(msg) = response
                    ec2.AddKeyValues(response)
                    learned = True
                End If
            Next

            Return learned
        End Function

        Public Function Respond(str1 As String) As String
            Return ec2.Response(str1)
        End Function

        Public Function RespondLatest(str1 As String) As String
            ' Get most recent reply/data
            Return ec2.ResponseLatest(str1)
        End Function

        Private Function GetPotentialMatchers(msg As String) As List(Of PhraseMatcher)
            Dim potentialMatchers As New List(Of PhraseMatcher)()
            For Each key As String In patternIndex.Keys
                If msg.Contains(key) Then
                    potentialMatchers.AddRange(patternIndex(key))
                End If
            Next
            Return potentialMatchers
        End Function

        Public Sub AddPhraseMatcher(pattern As String, ParamArray kvPairs As String())
            Dim kvs As New List(Of AXKeyValuePair)()
            For i As Integer = 0 To kvPairs.Length - 1 Step 2
                kvs.Add(New AXKeyValuePair(kvPairs(i), kvPairs(i + 1)))
            Next
            Dim matcher As New PhraseMatcher(pattern, kvs)
            babble2.Add(matcher)
            IndexPattern(pattern, matcher)
        End Sub

        Private Sub IndexPattern(pattern As String, matcher As PhraseMatcher)
            For Each word As String In pattern.Split(" "c)
                If Not patternIndex.ContainsKey(word) Then
                    patternIndex(word) = New List(Of PhraseMatcher)()
                End If
                patternIndex(word).Add(matcher)
            Next
        End Sub

        Public Class PhraseMatcher
            Public ReadOnly Matcher As Regex
            Public ReadOnly Responses As List(Of AXKeyValuePair)

            Public Sub New(matcher As String, responses As List(Of AXKeyValuePair))
                Me.matcher = New Regex(matcher)
                Me.responses = responses
            End Sub

            Public Function Matches(str As String) As Boolean
                Return matcher.IsMatch(str)
            End Function

            Public Function Respond(str As String) As List(Of AXKeyValuePair)
                Dim m As Match = matcher.Match(str)
                Dim result As New List(Of AXKeyValuePair)()
                If m.Success Then
                    Dim tmp As Integer = m.Groups.Count - 1 ' GroupCount in Java is equivalent to Groups.Count - 1 in .NET
                    For Each kv As AXKeyValuePair In Me.responses
                        Dim tempKV As New AXKeyValuePair(kv.GetKey(), kv.GetValue())
                        For i As Integer = 0 To tmp - 1
                            Dim s As String = m.Groups(i + 1).Value
                            tempKV.SetKey(tempKV.GetKey().Replace("{" & i & "}", s).ToLower())
                            tempKV.SetValue(tempKV.GetValue().Replace("{" & i & "}", s).ToLower())
                        Next
                        result.Add(tempKV)
                    Next
                End If
                Return result
            End Function
        End Class
    End Class

    Public Class ElizaDeducerInitializer
        Inherits ElizaDeducer

        ' Constructor
        Public Sub New(lim As Integer)
            ' Recommended lim = 5; it's the limit of responses per key in the EventChat dictionary.
            ' The purpose of the lim is to make saving and loading data easier.
            MyBase.New(lim)
            InitializeBabble2()
        End Sub

        ' Initialize the babble2 list with predefined phrase matchers
        Private Sub InitializeBabble2()
            AddPhraseMatcher(
                "(.*) is (.*)",
                "what is {0}", "{0} is {1}",
                "explain {0}", "{0} is {1}"
            )

            AddPhraseMatcher(
                "if (.*) or (.*) than (.*)",
                "{0}", "{2}",
                "{1}", "{2}"
            )

            AddPhraseMatcher(
                "if (.*) and (.*) than (.*)",
                "{0}", "{1}"
            )

            AddPhraseMatcher(
                "(.*) because (.*)",
                "{1}", "i guess {0}"
            )
        End Sub
    End Class

    Public Class ElizaDBWrapper
        ' This (function wrapper) class adds save/load functionality to the ElizaDeducer Object.
        ' Example usage:
        ' Dim ed As New ElizaDeducerInitializer(2)
        ' ed.GetEc2().AddFromDB("test", "one_two_three") ' Manual load for testing
        ' Dim k As New Kokoro(New AbsDictionaryDB()) ' Use skill's kokoro attribute
        ' Dim ew As New ElizaDBWrapper()
        ' Console.WriteLine(ew.Respond("test", ed.GetEc2(), k)) ' Get reply for input, tries loading reply from DB
        ' Console.WriteLine(ew.Respond("test", ed.GetEc2(), k)) ' Doesn't try DB load on second run
        ' ed.Learn("a is b") ' Learn only after respond
        ' ew.SleepNSave(ed.GetEc2(), k) ' Save when bot is sleeping, not on every skill input method visit

        Private ReadOnly modifiedKeys As New HashSet(Of String)()

        Public Function Respond(in1 As String, ec As EventChatV2, kokoro As Kokoro) As String
            If modifiedKeys.Contains(in1) Then
                Return ec.Response(in1)
            End If
            modifiedKeys.Add(in1)
            ' Load
            ec.AddFromDB(in1, kokoro.grimoireMemento.SimpleLoad(in1))
            Return ec.Response(in1)
        End Function

        Public Function RespondLatest(in1 As String, ec As EventChatV2, kokoro As Kokoro) As String
            If modifiedKeys.Contains(in1) Then
                Return ec.ResponseLatest(in1)
            End If
            modifiedKeys.Add(in1)
            ' Load and get latest reply for input
            ec.AddFromDB(in1, kokoro.grimoireMemento.SimpleLoad(in1))
            Return ec.ResponseLatest(in1)
        End Function

        Public Sub SleepNSave(ecv2 As EventChatV2, kokoro As Kokoro)
            For Each element As String In ecv2.GetModifiedKeys()
                kokoro.grimoireMemento.SimpleSave(element, ecv2.GetSaveStr(element))
            Next
        End Sub
    End Class

    Public Class QuestionChecker
        Private Shared ReadOnly QUESTION_WORDS As HashSet(Of String) = New HashSet(Of String) From {
        "what", "who", "where", "when", "why", "how",
        "is", "are", "was", "were", "do", "does", "did",
        "can", "could", "would", "will", "shall", "should",
        "have", "has", "am", "may", "might"
    }

        Public Shared Function IsQuestion(ByVal input As String) As Boolean
            If String.IsNullOrWhiteSpace(input) Then
                Return False
            End If

            Dim trimmed As String = input.Trim().ToLower()

            ' Check for question mark
            If trimmed.EndsWith("?") Then
                Return True
            End If

            ' Extract the first word
            Dim firstSpace As Integer = trimmed.IndexOf(" "c)
            Dim firstWord As String = If(firstSpace = -1, trimmed, trimmed.Substring(0, firstSpace))

            ' Check for contractions like "who's"
            If firstWord.Contains("'") Then
                firstWord = firstWord.Substring(0, firstWord.IndexOf("'"c))
            End If

            ' Check if first word is a question word
            Return QUESTION_WORDS.Contains(firstWord)
        End Function
    End Class

    Public Class PhraseInflector
        ' Dictionary for pronoun and verb inflection
        Private Shared ReadOnly inflectionMap As New Dictionary(Of String, String) From {
        {"i", "you"},
        {"me", "you"},
        {"my", "your"},
        {"mine", "yours"},
        {"you", "i"}, ' Default inflection
        {"your", "my"},
        {"yours", "mine"},
        {"am", "are"},
        {"are", "am"},
        {"was", "were"},
        {"were", "was"},
        {"i'd", "you would"},
        {"i've", "you have"},
        {"you've", "I have"},
        {"you'll", "I will"}
    }

        ' Function to inflect a phrase
        Public Shared Function InflectPhrase(ByVal phrase As String) As String
            Dim words As String() = phrase.Split(" "c)
            Dim result As New Text.StringBuilder()

            For i As Integer = 0 To words.Length - 1
                Dim word As String = words(i)
                Dim lowerWord As String = word.ToLower()
                Dim inflectedWord As String = word ' Default to the original word

                ' Check if the word needs to be inflected
                If inflectionMap.ContainsKey(lowerWord) Then
                    inflectedWord = inflectionMap(lowerWord)

                    ' Special case for "you"
                    If lowerWord = "you" Then
                        ' Inflect to "me" if it's at the end of the sentence or after a verb
                        If i = words.Length - 1 OrElse (i > 0 AndAlso IsVerb(words(i - 1).ToLower())) Then
                            inflectedWord = "me"
                        Else
                            inflectedWord = "I"
                        End If
                    End If
                End If

                ' Preserve capitalization
                If Char.IsUpper(word(0)) Then
                    inflectedWord = inflectedWord.Substring(0, 1).ToUpper() & inflectedWord.Substring(1)
                End If

                result.Append(inflectedWord).Append(" ")
            Next

            Return result.ToString().Trim()
        End Function

        ' Helper function to check if a word is a verb
        Private Shared Function IsVerb(ByVal word As String) As Boolean
            Return word = "am" OrElse word = "are" OrElse word = "was" OrElse word = "were" OrElse
               word = "have" OrElse word = "has" OrElse word = "had" OrElse
               word = "do" OrElse word = "does" OrElse word = "did"
        End Function
    End Class

    Public Class RailBot
        Private ReadOnly ec As EventChatV2
        Private context As String = "stand by"
        Private elizaWrapper As ElizaDBWrapper = Nothing ' Starts as Nothing (no DB)

        ' Constructor with limit parameter
        Public Sub New(ByVal limit As Integer)
            ec = New EventChatV2(limit)
        End Sub

        ' Default constructor
        Public Sub New()
            Me.New(5)
        End Sub

        ''' <summary>
        ''' Enables database features. Must be called before any save/load operations.
        ''' If never called, RailBot works in memory-only mode.
        ''' </summary>
        Public Sub EnableDBWrapper()
            If elizaWrapper Is Nothing Then
                elizaWrapper = New ElizaDBWrapper()
            End If
        End Sub

        ''' <summary>
        ''' Disables database features.
        ''' </summary>
        Public Sub DisableDBWrapper()
            elizaWrapper = Nothing
        End Sub

        ''' <summary>
        ''' Sets the context.
        ''' </summary>
        ''' <param name="newContext">The new context string.</param>
        Public Sub SetContext(ByVal newContext As String)
            If String.IsNullOrEmpty(newContext) Then
                Return
            End If
            context = newContext
        End Sub

        ''' <summary>
        ''' Private helper method for monolog response.
        ''' </summary>
        Private Function RespondMonolog(ByVal ear As String) As String
            If String.IsNullOrEmpty(ear) Then
                Return ""
            End If

            Dim temp As String = ec.Response(ear)
            If Not String.IsNullOrEmpty(temp) Then
                context = temp
            End If
            Return temp
        End Function

        ''' <summary>
        ''' Learns a new response for the current context.
        ''' </summary>
        Public Sub Learn(ByVal ear As String)
            If String.IsNullOrEmpty(ear) OrElse ear.Equals(context) Then
                Return
            End If
            ec.AddKeyValue(context, ear)
            context = ear
        End Sub

        ''' <summary>
        ''' Returns a monolog based on the current context.
        ''' </summary>
        Public Function Monolog() As String
            Return RespondMonolog(context)
        End Function

        ''' <summary>
        ''' Responds to a dialog input.
        ''' </summary>
        Public Function RespondDialog(ByVal ear As String) As String
            Return ec.Response(ear)
        End Function

        ''' <summary>
        ''' Responds to the latest input.
        ''' </summary>
        Public Function RespondLatest(ByVal ear As String) As String
            Return ec.ResponseLatest(ear)
        End Function

        ''' <summary>
        ''' Adds a new key-value pair to the memory.
        ''' </summary>
        Public Sub LearnKeyValue(ByVal newContext As String, ByVal reply As String)
            ec.AddKeyValue(newContext, reply)
        End Sub

        ''' <summary>
        ''' Feeds a list of key-value pairs into the memory.
        ''' </summary>
        Public Sub FeedKeyValuePairs(ByVal kvList As List(Of AXKeyValuePair))
            If kvList.Count = 0 Then
                Return
            End If
            For Each kv As AXKeyValuePair In kvList
                LearnKeyValue(kv.GetKey(), kv.GetValue())
            Next
        End Sub

        ''' <summary>
        ''' Saves learned data using the provided Kokoro instance.
        ''' </summary>
        Public Sub SaveLearnedData(ByVal kokoro As Kokoro)
            If elizaWrapper Is Nothing Then
                Return
            End If
            elizaWrapper.SleepNSave(ec, kokoro)
        End Sub

        ''' <summary>
        ''' Private helper method for loadable monolog mechanics.
        ''' </summary>
        Private Function LoadableMonologMechanics(ByVal ear As String, ByVal kokoro As Kokoro) As String
            If String.IsNullOrEmpty(ear) Then
                Return ""
            End If

            Dim temp As String = elizaWrapper.Respond(ear, ec, kokoro)
            If Not String.IsNullOrEmpty(temp) Then
                context = temp
            End If
            Return temp
        End Function

        ''' <summary>
        ''' Returns a loadable monolog based on the current context.
        ''' </summary>
        Public Function LoadableMonolog(ByVal kokoro As Kokoro) As String
            If elizaWrapper Is Nothing Then
                Return Monolog()
            End If
            Return LoadableMonologMechanics(context, kokoro)
        End Function

        ''' <summary>
        ''' Returns a loadable dialog response.
        ''' </summary>
        Public Function LoadableDialog(ByVal ear As String, ByVal kokoro As Kokoro) As String
            If elizaWrapper Is Nothing Then
                Return RespondDialog(ear)
            End If
            Return elizaWrapper.Respond(ear, ec, kokoro)
        End Function
    End Class


End Module