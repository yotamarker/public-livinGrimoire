Module skills
    Public Class DiTime
        Inherits DiSkillV2
        Public Sub New()
            MyBase.New()
        End Sub
        ' Override
        Public Overrides Sub Input(ear As String, skin As String, eye As String)
            Select Case ear
                Case "what is the date"
                    Me.SetVerbatimAlg(4, $"{TimeUtils.getCurrentMonthDay()} {TimeUtils.getCurrentMonthName()} {TimeUtils.GetCurrentYear()}")
                Case "what is the time"
                    Me.SetVerbatimAlg(4, TimeUtils.getCurrentTimeStamp())
                Case "honey bunny"
                    Me.SetVerbatimAlg(4, "bunny honey")
                Case "i am sleepy"
                    Me.SetSimpleAlg("Chi… Chi knows it’s late. Sleep, sleep is good. When you sleep, you can dream. Dreams, dreams are nice. Tomorrow, lots to do. If sleep now, can do best tomorrow. So, let’s sleep. Good night… zzz…")
                Case "which day is it"
                    Me.SetVerbatimAlg(4, TimeUtils.getDayOfDWeek())
                Case "good morning"
                    Me.SetVerbatimAlg(4, $"good {TimeUtils.PartOfDay()}") ' fstring
                Case "good night"
                    Me.SetVerbatimAlg(4, $"good {TimeUtils.PartOfDay()}") ' fstring
                Case "good afternoon"
                    Me.SetVerbatimAlg(4, $"good {TimeUtils.PartOfDay()}") ' fstring
                Case "good evening"
                    Me.SetVerbatimAlg(4, $"good {TimeUtils.PartOfDay()}") ' fstring
                Case "which month is it"
                    Me.SetVerbatimAlg(4, TimeUtils.getCurrentMonthName())
                Case "which year is it"
                    Me.SetVerbatimAlg(4, $"{TimeUtils.GetCurrentYear}")
                Case "what is your time zone"
                    Me.SetVerbatimAlg(4, TimeUtils.GetLocalTimeZone())
                Case "incantation 0"
                    Me.SetVerbatimAlg(5, "fly", "bless of magic caster", "infinity wall", "magic ward holy", "life essen")
            End Select
        End Sub
    End Class
    Public Class DiBicameral
        Inherits DiSkillV2
        ' DiBicameral bicameral = New DiBicameral()
        ' bicameral.msgCol.addMSGV2("02:57", "test run ok")
        ' Add # for messages that engage other skills

        Public msgCol As New TimedMessages()

        Public Overrides Sub input(ByVal ear As String, ByVal skin As String, ByVal eye As String)
            msgCol.Tick()

            If Not kokoro.toHeart("dibicameral").Equals("null") Then
                kokoro.toHeart("dibicameral") = "null"
            End If

            If msgCol.GetMsg() Then
                Dim temp As String = msgCol.GetLastMSG()
                If Not temp.Contains("#") Then
                    SetSimpleAlg(temp)
                Else
                    kokoro.toHeart("dibicameral") = temp.Replace("#", "")
                End If
            End If
        End Sub
        Public Overrides Sub SetKokoro(kokoro As Kokoro)
            MyBase.SetKokoro(kokoro)
            kokoro.toHeart("dibicameral") = "null"
        End Sub
    End Class
End Module
