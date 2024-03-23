Imports System.Text.RegularExpressions
Module Auxiliary_modules
    Public Class RegexUtil
        Public Function ExtractRegex(ByVal regexStr As String, ByVal ear As String) As String
            Dim regex As New Regex(regexStr)
            Dim match As Match = regex.Match(ear)

            If match.Success Then
                Return match.Value
            Else
                Return String.Empty
            End If
        End Function
    End Class
End Module
