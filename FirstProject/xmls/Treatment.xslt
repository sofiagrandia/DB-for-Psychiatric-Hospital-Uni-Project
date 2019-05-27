<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <p><b>TREATMENTS</b></p>
   <table border = "1">
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
   </html>
</xsl:template>
</xsl:stylesheet>