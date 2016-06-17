<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
    xmlns:ns3="aktovi" xmlns:am="amandmani" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xlink="http://www.w3.org/1999/xlink"
    >
    <xsl:template match="/">
        <html>
            <head>
                <meta charset="UTF-8"/>
                
                <p>Стање амандмана: 
                    <xsl:value-of select="am:Amandman/am:Stanje"/>
                </p>
                <h1 style="text-align:center;">Амандман се односи на </h1>
                <h2 style="text-align:center;"><a href="http://localhost:8080/#/viewAkt/{am:Amandman/@IdAct}"><xsl:value-of select="am:Amandman/am:Kontekst"/></a></h2>
                <p style="text-align:center;">На предлог одборника <xsl:value-of select="am:Amandman/@Ko_dodaje"/></p>    
            </head>
            <body style="font-family: Calibri">
            	<xsl:for-each select="am:Amandman">
            	    <xsl:apply-templates select="am:Podamandman"/>	
				</xsl:for-each>
                <p>Образложење: 
                    <xsl:value-of select="am:Amandman/am:Obrazlozenje"/>
                </p>
                <p>Овлашћено лице: 
                    <xsl:value-of select="am:Amandman/am:Ovlasceno_lice"/>
                </p>
		 	</body>
            
        </html>
    </xsl:template>
    
    <xsl:template match="am:Amandman">
        
        <xsl:choose>
            <xsl:when test="count(am:Podamandman) &gt; 0">
                <ol>
                    <xsl:apply-templates select="am:Podamandman"/>
                </ol>
            </xsl:when>
            <xsl:when test="count(am:Obrazlozenje) &gt; 0">
                <p><xsl:value-of select="text()"/></p>
            </xsl:when>
            <xsl:when test="count(am:Ovlasceno_lice) &gt; 0">
                <p><xsl:value-of select="text()"/></p>
            </xsl:when>
            <xsl:when test="count(am:Stanje) &gt; 0">
                <p>Амандман је у стању: <xsl:value-of select="text()"/></p>
            </xsl:when>
            <xsl:otherwise>
                <ol>
                    <xsl:value-of select="."/>
                </ol>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    
    <xsl:template match="am:Podamandman">
        <p style="text-align:center;"><b><xsl:value-of select="am:Operacija"/></b></p>
        <xsl:choose>
            <xsl:when test="count(am:Sadrzaj) &gt; 0">
                <ol>
                    <xsl:apply-templates select="am:Sadrzaj"/>
                </ol>
            </xsl:when>
            <xsl:otherwise>
                <ol>
                    <xsl:value-of select="."/>
                </ol>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    
    <xsl:template match="am:Sadrzaj">
        <p><xsl:value-of select="text()"/></p>
        <xsl:choose>
            <xsl:when test="count(ns3:Glavni_deo) &gt; 0">
                <p><xsl:value-of select="text()"/></p>
                <p style="text-align:center;"><xsl:value-of select="ns3:Glavni_deo"/></p>
            </xsl:when>
            <xsl:when test="count(ns3:Tacka) &gt; 0">
                <p style="text-align:center;">се односи на тачку <xsl:value-of select="ns3:Tacka/@broj"/></p>
                <p style="text-align:center;"><xsl:value-of select="ns3:Tacka"/></p>
            </xsl:when>
            <xsl:when test="count(ns3:Stav) &gt; 0">
                <p style="text-align:center;">се односи на став <xsl:value-of select="ns3:Stav/@Id"/></p>
                <p style="text-align:center;"><xsl:value-of select="ns3:Stav"/></p>
            </xsl:when>
            <xsl:when test="count(ns3:Podtacka) &gt; 0">
                <p style="text-align:center;">се односи на подтачку <xsl:value-of select="ns3:Podtacka/@broj"/></p>
                <p style="text-align:center;"><xsl:value-of select="ns3:Podtacka"/></p>
            </xsl:when>
            <xsl:when test="count(ns3:Alineja) &gt; 0">
                <p><xsl:value-of select="text()"/></p>
                <p style="text-align:center;"><xsl:value-of select="ns3:Alineja"/></p>
            </xsl:when>
            <xsl:otherwise>
                <ol>
                    <xsl:value-of select="."/>
                </ol>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    
    <xsl:template match="am:Operacija">
        <p>
            <b><xsl:value-of select="."/></b>
        </p>
    </xsl:template>
</xsl:stylesheet>