<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
    xmlns:ns3="aktovi" xmlns:am="amandmani" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns="http://www.w3.org/1999/xhtml"
    >
    <xsl:variable name="AktId" select="am:Amandman/@IdAct" />
    <xsl:template match="/">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <div style="text-align: center; margin-bottom: 15px">
                    <img src="http://www.021.rs/images/uploads/grb_novog_sada.jpg" height="120" width="150"/>
                </div>
                <h1 style="text-align:center;">Амандман се односи на </h1>
                <h2 style="text-align:center;"><a href="http://localhost:8080/#/viewAkt/{am:Amandman/@IdAct}"><xsl:value-of select="am:Amandman/am:Kontekst"/></a></h2>
                <p style="text-align:center;">На предлог одборника <xsl:value-of select="am:Amandman/@Ko_dodaje"/></p>    
            </head>
            <body style="font-family: Calibri">
            	<xsl:for-each select="am:Amandman">
            	    <xsl:apply-templates select="am:Podamandman"/>	
				</xsl:for-each>
                
                <p>Стање амандмана: 
                    <xsl:value-of select="am:Amandman/am:Stanje"/>
                </p>
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
        <p style="text-align:center;"><b><xsl:value-of select="am:Operacija"/> се односи на </b></p>
        <xsl:choose>
            <xsl:when test="count(am:Sadrzaj) &gt; 0">
                    <xsl:apply-templates select="am:Sadrzaj"/>
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
                <p style="text-align:center;"><a href="http://localhost:8080/#/viewAkt/{$AktId}#Tacka{ns3:Tacka/@broj}">Тачка <xsl:value-of select="ns3:Tacka/@broj"/></a></p>
                <p style="text-align:center;"><xsl:value-of select="ns3:Tacka"/></p>
            </xsl:when>
            <xsl:when test="count(ns3:Clan) &gt; 0">
                <p style="text-align:center;"><a href="http://localhost:8080/#/viewAkt/{$AktId}#Clan{ns3:Clan/@Broj_clana}">Члан <xsl:value-of select="ns3:Clan/@Broj_clana"/></a></p>
                <p style="text-align:center;"><xsl:value-of select="ns3:Clan"/></p>
            </xsl:when>
            <xsl:when test="count(ns3:Stav) &gt; 0">
                <p style="text-align:center;"><a href="http://localhost:8080/#/viewAkt/{$AktId}#Stav{ns3:Stav/@Id}">Став <xsl:value-of select="ns3:Stav/@Id"/></a></p>
                <p style="text-align:center;"><xsl:value-of select="ns3:Stav"/></p>
            </xsl:when>
            <xsl:when test="count(ns3:Podtacka) &gt; 0">
                <p style="text-align:center;"><a href="http://localhost:8080/#/viewAkt/{$AktId}#Stav{ns3:Podtacka/@Id}">Подтачка <xsl:value-of select="ns3:Podtacka/@broj"/></a></p>
                <p style="text-align:center;"><xsl:value-of select="ns3:Podtacka"/></p>
            </xsl:when>
            <xsl:when test="count(ns3:Alineja) &gt; 0">
                <p style="text-align:center;"><a href="http://localhost:8080/#/viewAkt/{$AktId}#Alineja{ns3:Alineja/@Id}">Алинеја <xsl:value-of select="ns3:Alineja/@Id"/></a></p>
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