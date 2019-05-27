<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <body bgcolor="F1F1F1">
   <title>FOR YOU DATA BASE</title>
   <p><b><FONT FACE = "consolas" SIZE = "8"><center> - PSYCHIATRIC HOSPITAL - </center></FONT></b></p>
   <p><b><FONT FACE = "helvetica" SIZE = "5"><center> - TREATMENTS - </center></FONT></b></p>
   
   <table border = "5" bordercolor="#0000FF" bordercolorlight="#33CCFF" cellspacing = "5" width="50%" align="center">
   <th>ID</th>
   <th>TYPE</th>
   <th>NUMBER</th>
   <xsl:for-each select="TreatmentList/Treatment">
   <xsl:sort select="@id" />
   <tr>
   <td><i><xsl:value-of select="@id" /></i></td>
   <td><xsl:value-of select="@type" /></td>
   <td><xsl:value-of select="@number" /></td>
   </tr>
   </xsl:for-each>
   </table>
   </body>
   </html>
</xsl:template>
</xsl:stylesheet>