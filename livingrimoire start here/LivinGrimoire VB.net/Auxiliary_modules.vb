Imports System.Text.RegularExpressions
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

End Module
