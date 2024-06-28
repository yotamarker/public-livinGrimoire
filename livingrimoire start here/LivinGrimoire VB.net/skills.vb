﻿Module skills
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
    Public Class DiSkillBundle
        Inherits DiSkillV2

        Protected axSkillBundle As New AXSkillBundle()

        Public Overrides Sub Input(ByVal ear As String, ByVal skin As String, ByVal eye As String)
            Dim a1 As AlgorithmV2 = axSkillBundle.DispenseAlgorithm(ear, skin, eye)
            If a1 Is Nothing Then
                Return
            End If
            Me.outAlg = a1.GetAlg()
            Me.outpAlgPriority = a1.GetPriority()
        End Sub

        Public Overrides Sub SetKokoro(ByVal kokoro As Kokoro)
            MyBase.SetKokoro(kokoro)
            axSkillBundle.SetKokoro(kokoro)
        End Sub

        Public Sub AddSkill(ByVal skill As DiSkillV2)
            axSkillBundle.AddSkill(skill)
        End Sub
    End Class
    Public Class SkillBranch
        Inherits DiSkillV2

        ' unique skill used to bind similar skills
        ' contains collection of skills
        ' mutates active skill if detects conjuration
        ' mutates active skill if algorithm results in
        ' negative feedback
        ' positive feedback negates active skill mutation

        Private skillRef As New Hashtable()
        Private skillHub As New SkillHubAlgDispenser()
        Private ml As AXLearnability

        Public Sub New(tolerance As Integer)
            ml = New AXLearnability(tolerance)
        End Sub

        Public Overrides Sub input(ear As String, skin As String, eye As String)
            ' conjuration alg morph
            If skillRef.ContainsKey(ear) Then
                skillHub.setActiveSkillWithMood(skillRef(ear))
                SetSimpleAlg("hmm")
            End If

            ' machine learning alg morph
            If ml.MutateAlg(ear) Then
                skillHub.cycleActiveSkill()
                SetSimpleAlg("hmm")
            End If

            ' alg engage
            Dim a1 As AlgorithmV2 = skillHub.dispenseAlgorithm(ear, skin, eye)
            If a1 Is Nothing Then Return

            Me.outAlg = a1.GetAlg()
            Me.outpAlgPriority = a1.GetPriority()
            ml.PendAlg()
        End Sub

        Public Sub addSkill(skill As DiSkillV2)
            skillHub.addSkill(skill)
        End Sub

        Public Sub addReferencedSkill(skill As DiSkillV2, conjuration As String)
            ' the conjuration string will engage its respective skill
            skillHub.addSkill(skill)
            skillRef(conjuration) = skillHub.getSize()
        End Sub

        ' learnability params
        Public Sub addDefcon(defcon As String)
            ml.defcons.Add(defcon)
        End Sub

        Public Sub addGoal(goal As String)
            ml.goals.Add(goal)
        End Sub

        ' while alg is pending, cause alg mutation ignoring learnability tolerance:
        Public Sub addDefconLV5(defcon5 As String)
            ml.defcon5.Add(defcon5)
        End Sub

        Public Overrides Sub setKokoro(kokoro As Kokoro)
            MyBase.SetKokoro(kokoro)
            skillHub.setKokoro(kokoro)
        End Sub
    End Class
    Public Class GamiPlus
        Inherits DiSkillV2

        ' The grind side of the game, see GamificationN for the reward side
        Private ReadOnly gain As Integer
        Private ReadOnly skill As DiSkillV2
        Private ReadOnly axGamification As AXGamification

        Public Sub New(skill As DiSkillV2, axGamification As AXGamification, gain As Integer)
            Me.skill = skill
            Me.axGamification = axGamification
            Me.gain = gain
        End Sub

        Public Overrides Sub Input(ear As String, skin As String, eye As String)
            skill.Input(ear, skin, eye)
        End Sub

        Public Overrides Sub Output(noiron As Neuron)
            ' Skill activation increases gaming credits
            If skill.PendingAlgorithm() Then
                axGamification.IncrementBy(gain)
            End If
            skill.Output(noiron)
        End Sub

        Public Overrides Sub SetKokoro(kokoro As Kokoro)
            Me.skill.SetKokoro(kokoro)
        End Sub
    End Class
    Public Class GamiMinus
        Inherits DiSkillV2

        Private ReadOnly axGamification As AXGamification
        Private ReadOnly cost As Integer
        Private ReadOnly skill As DiSkillV2

        Public Sub New(skill As DiSkillV2, axGamification As AXGamification, cost As Integer)
            Me.skill = skill
            Me.axGamification = axGamification
            Me.cost = cost
        End Sub

        Public Overrides Sub Input(ear As String, skin As String, eye As String)
            ' Engage skill only if a reward is possible
            If axGamification.Surplus(cost) Then
                skill.Input(ear, skin, eye)
            End If
        End Sub

        Public Overrides Sub Output(noiron As Neuron)
            ' Charge reward if an algorithm is pending
            If skill.PendingAlgorithm() Then
                axGamification.Reward(cost)
                skill.Output(noiron)
            End If
        End Sub

        Public Overrides Sub SetKokoro(kokoro As Kokoro)
            Me.skill.SetKokoro(kokoro)
        End Sub
    End Class
    Public Class DiGamificationSkillBundle
        Inherits DiSkillBundle

        Private ReadOnly axGamification As New AXGamification()
        Private gain As Integer = 1
        Private cost As Integer = 2

        Public Sub SetGain(gain As Integer)
            If gain > 0 Then
                Me.gain = gain
            End If
        End Sub

        Public Sub SetCost(cost As Integer)
            If cost > 0 Then
                Me.cost = cost
            End If
        End Sub

        Public Sub AddGrindSkill(skill As DiSkillV2)
            axSkillBundle.AddSkill(New GamiPlus(skill, axGamification, gain))
        End Sub

        Public Sub AddCostlySkill(skill As DiSkillV2)
            axSkillBundle.AddSkill(New GamiMinus(skill, axGamification, cost))
        End Sub

        Public Function GetAxGamification() As AXGamification
            Return axGamification
        End Function
    End Class
    Public Class DiGamificationScouter
        Inherits DiSkillV2

        Private lim As Integer = 2 ' Minimum for mood
        Private ReadOnly axGamification As AXGamification
        Private ReadOnly noMood As New Responder("bored", "no emotions detected", "neutral")
        Private ReadOnly yesMood As New Responder("operational", "efficient", "mission ready", "awaiting orders")

        Public Sub New(axGamification As AXGamification)
            Me.axGamification = axGamification
        End Sub

        Public Sub SetLim(lim As Integer)
            Me.lim = lim
        End Sub

        Public Overrides Sub Input(ear As String, skin As String, eye As String)
            If Not ear.Equals("how are you") Then
                Return
            End If
            If axGamification.GetCounter() > lim Then
                SetSimpleAlg(yesMood.GetAResponse())
            Else
                SetSimpleAlg(noMood.GetAResponse())
            End If
        End Sub
    End Class
End Module
