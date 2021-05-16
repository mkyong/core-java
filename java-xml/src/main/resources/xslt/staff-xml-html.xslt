<?xml version="1.0" encoding="UTF-8"?>
<html xsl:version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <body style="font-size:12pt;color:#FFFFFF;background-color:#000000">
        <h1 style="font-size:20pt;color:#FF0000">Hello World DOM + XML + XSLT</h1>
        <xsl:for-each select="company/staff">
            <ul>
                <li>
                    <xsl:value-of select="@id"/> -
                    <xsl:value-of select="name"/> -
                    <xsl:value-of select="role"/>
                </li>
            </ul>
        </xsl:for-each>
    </body>
</html>