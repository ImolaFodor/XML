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
            	<xsl:for-each select="ns3:Pravni_akt">
            	    <xsl:apply-templates select="ns3:Glavni_deo"/>
					
				</xsl:for-each>
				<p>Држава: 
				<xsl:value-of select="ns3:Pravni_akt/ns3:Drzava"/>
				</p>
				<p>Покрајина: 
				<xsl:value-of select="ns3:Pravni_akt/ns3:Pokrajina"/>
				</p>
				<p>Град: 
				<xsl:value-of select="ns3:Pravni_akt/ns3:Grad"/>
				</p>
				<p>Скупштина: 
				<xsl:value-of select="ns3:Pravni_akt/ns3:Skupstina"/>
				</p>
				<p>Број прописа код органа: 
				<xsl:value-of select="ns3:Pravni_akt/ns3:Broj_popisa_kod_organa"/>
				</p>
				<p>Датум доношења прописа: 
				<xsl:value-of select="ns3:Pravni_akt/ns3:Datum_donosenja_propisa"/>
				</p>
				<p>Место доношења прописа: 
				<xsl:value-of select="ns3:Pravni_akt/ns3:Mesto_donosenja_propisa"/>
				</p>
                <p>Стање прописа: 
                    <xsl:value-of select="ns3:Pravni_akt/ns3:Stanje"/>
                </p>
               
            </body>
            
        </html>
    </xsl:template>
    
    <xsl:template match="ns3:Glavni_deo">
        
        <xsl:choose>
            <xsl:when test="count(ns3:Deo) &gt; 0">
                
                <p><xsl:value-of select="text()"/></p>
                <ol>
                    <xsl:apply-templates select="ns3:Deo"/>
                </ol>
            </xsl:when>
            <xsl:when test="count(ns3:Glava) &gt; 0">
                <p><xsl:value-of select="text()"/></p>
                <xsl:apply-templates select="ns3:Glava"/>
            </xsl:when>
            <xsl:when test="count(ns3:Odeljak) &gt; 0">
                <p><xsl:value-of select="text()"/></p>
                <ol>
                    <xsl:apply-templates select="ns3:Odeljak"/>
                </ol>
            </xsl:when>
            <xsl:when test="count(ns3:Clan) &gt; 0">
                <p><xsl:value-of select="text()"/></p>
                <xsl:apply-templates select="ns3:Clan"/>
            </xsl:when>
            <xsl:otherwise>
                <ol>
                    <xsl:value-of select="."/>
                </ol>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    
    <xsl:template match="ns3:Deo">
        <p style="text-align:center;">Део <xsl:value-of select="@Redni_broj"/></p>
        <p style="text-align:center;"><xsl:value-of select="@Naziv"/></p>
        <xsl:choose>
            <xsl:when test="count(ns3:Glava) &gt; 0">
                <p><xsl:value-of select="text()"/></p>
                <ol>
                    <xsl:apply-templates select="ns3:Glava"/>
                </ol>
            </xsl:when>
            <xsl:when test="count(ns3:Odeljak) &gt; 0">
                <p><xsl:value-of select="text()"/></p>
                <ol>
                    <xsl:apply-templates select="ns3:Odeljak"/>
                </ol>
            </xsl:when>
            <xsl:when test="count(ns3:Clan) &gt; 0">
                <p><xsl:value-of select="text()"/></p>
                <xsl:apply-templates select="ns3:Clan"/>
            </xsl:when>
            <xsl:otherwise>
                <ol>
                    <xsl:value-of select="."/>
                </ol>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    
    <xsl:template match="ns3:Glava">
        <p style="text-align:center;"><xsl:value-of select="@Naziv"/></p>
        <xsl:choose>
            <xsl:when test="count(ns3:Odeljak) &gt; 0">
                <p><xsl:value-of select="text()"/></p>
                <ol>
                    <xsl:apply-templates select="ns3:Odeljak"/>
                </ol>
            </xsl:when>
            <xsl:when test="count(ns3:Clan) &gt; 0">
                <p><xsl:value-of select="text()"/></p>
                <ol>
                    <xsl:apply-templates select="ns3:Clan"/>
                </ol>
            </xsl:when>
            <xsl:otherwise>
                <ol>
                    <xsl:value-of select="."/>
                </ol>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    
    <xsl:template match="ns3:Odeljak">
        <p style="text-align:center;"><xsl:value-of select="@Naziv"/></p>
        <xsl:choose>
            <xsl:when test="count(ns3:Odeljak) &gt; 0">
                <p><xsl:value-of select="text()"/></p>
                <ol>
                    <xsl:apply-templates select="ns3:Odeljak"/>
                </ol>
            </xsl:when>
            <xsl:when test="count(ns3:Clan) &gt; 0">
                <p><xsl:value-of select="text()"/></p>
                <ol>
                    <xsl:apply-templates select="ns3:Clan"/>
                </ol>
            </xsl:when>
            <xsl:otherwise>
                <ol>
                    <xsl:value-of select="."/>
                </ol>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    
    <xsl:template match="ns3:Clan">
        <p style="text-align:center;">Члан <xsl:value-of select="@Broj_clana"/></p>
        <p style="text-align:center;"><xsl:value-of select="@Naziv"/></p>
        <xsl:choose>
            <xsl:when test="count(ns3:Stav) &gt; 0">
                <p><xsl:value-of select="text()"/></p>
                <ol>
                    <xsl:apply-templates select="ns3:Stav"/>
                </ol>
            </xsl:when>
            <xsl:otherwise>
                <ol>
                    <xsl:value-of select="."/>
                </ol>
            </xsl:otherwise>
        </xsl:choose>
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
        <p style="text-align:center;">Tacka <xsl:value-of select="@broj"/></p>
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
        <p style="text-align:center;">Podtacka <xsl:value-of select="@broj"/></p>
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
