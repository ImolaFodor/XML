<xsl:stylesheet version="1.0" 
    xmlns:ns3="aktovi" xmlns="http://www.w3.org/1999/xhtml" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xlink="http://www.w3.org/1999/xlink"
    >
    <xsl:template match="/">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <xsl:for-each select="ns3:Pravni_akt">
            		<p>
					<xsl:value-of select="ns3:Preambula"/>
					</p>
				</xsl:for-each>
					<h style="text-align:center;"><b><xsl:value-of select="ns3:Pravni_akt/ns3:Naziv"/></b></h>      
                    
            </head>
            <body style="font-family: Calibri">
            	<xsl:for-each select="ns3:Pravni_akt/ns3:Glavni_deo/ns3:Glava">
            		<p style="text-align:center;">
					<xsl:value-of select="@Naziv"/>
					</p>
					
					<xsl:for-each select="ns3:Clan">
            		<p style="text-align:center;">
					Clan <xsl:value-of select="@Broj_clana"/>
						
						<xsl:apply-templates select="ns3:Stav"/>	
					</p>
					</xsl:for-each>
					
				</xsl:for-each>
				<p>Drzava: 
				<xsl:value-of select="ns3:Pravni_akt/ns3:Drzava"/>
				</p>
				<p>Pokrajina: 
				<xsl:value-of select="ns3:Pravni_akt/ns3:Pokrajina"/>
				</p>
				<p>Grad: 
				<xsl:value-of select="ns3:Pravni_akt/ns3:Grad"/>
				</p>
				<p>Skupstina: 
				<xsl:value-of select="ns3:Pravni_akt/ns3:Skupstina"/>
				</p>
				<p>Broj popisa kod organa: 
				<xsl:value-of select="ns3:Pravni_akt/ns3:Broj_popisa_kod_organa"/>
				</p>
				<p>Datum donosenja propisa: 
				<xsl:value-of select="ns3:Pravni_akt/ns3:Datum_donosenja_propisa"/>
				</p>
				<p>Mesto donosenja propisa: 
				<xsl:value-of select="ns3:Pravni_akt/ns3:Mesto_donosenja_propisa"/>
				</p>
               
            </body>
            
        </html>
    </xsl:template>
    
    <xsl:template match="ns3:Stav">
    	
        <xsl:choose>
            <xsl:when test="count(ns3:Tacka) &gt; 0">
            <p><xsl:value-of select="text()"/></p>
                <ol>
                    <xsl:apply-templates select="ns3:Tacka"/>
                </ol>
            </xsl:when>
            <xsl:when test="count(ns3:Alineja) &gt; 0">
            <p><xsl:value-of select="text()"/></p>
                <ol>
                    <xsl:apply-templates select="ns3:Alineja"/>
                </ol>
            </xsl:when>
            <xsl:when test="count(ns3:Reference) &gt; 0">
            <p><xsl:value-of select="text()"/></p>
                <xsl:apply-templates select="ns3:Reference"/>
            </xsl:when>
            <xsl:otherwise>
                <ol>
                    <xsl:value-of select="."/>
                </ol>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    
    <xsl:template match="ns3:Alineja">
        <p>
            <xsl:value-of select="."/>
        </p>
    </xsl:template>
    
    <xsl:template match="ns3:Reference">
        <p>
            <a href="{@href}"><xsl:apply-templates/></a>
        </p>
    </xsl:template>
    
    <xsl:template match="ns3:Tacka">
        <xsl:choose>
            <xsl:when test="count(ns3:Podtacka) &gt; 0">
                <ol>
                    <xsl:apply-templates select="ns3:Podtacka"/>
                </ol>
            </xsl:when>
            <xsl:when test="count(ns3:Alineja) &gt; 0">
                <ol>
                    <xsl:apply-templates select="ns3:Alineja"/>
                </ol>
            </xsl:when>
            <xsl:otherwise>
                <ol>
                    <xsl:value-of select="."/>
                </ol>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    
    <xsl:template match="ns3:Podtacka">
        <xsl:choose>
            <xsl:when test="count(ns3:Alineja) &gt; 0">
                <ol>
                    <xsl:apply-templates select="ns3:Alineja"/>
                </ol>
            </xsl:when>
            <xsl:otherwise>
                <ol>
                    <xsl:value-of select="."/>
                </ol>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>   
</xsl:stylesheet>  
