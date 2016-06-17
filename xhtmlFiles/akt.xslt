<xsl:stylesheet version="1.0" 
    xmlns:ns3="aktovi" xmlns="http://www.w3.org/1999/xhtml" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xlink="http://www.w3.org/1999/xlink"
    >
    <xsl:variable name="AktId" select="ns3:Pravni_akt/@Id" />
    <xsl:variable name="ClanId" select="ns3:Pravni_akt/ns3:Clan/@Broj_clana" />
    <xsl:template match="/">
        
        <html>
            <head>
                <meta charset="UTF-8"/>
                <xsl:for-each select="ns3:Pravni_akt">
                    
            		<p>
            		    <div style="text-align: center; margin-bottom: 15px">
            		        <img src="http://www.021.rs/images/uploads/grb_novog_sada.jpg" height="120" width="150"/>
            		    </div>
					<xsl:value-of select="ns3:Preambula"/>
					</p>
				</xsl:for-each>
					<p style="text-align:center;"><b><xsl:value-of select="ns3:Pravni_akt/ns3:Naziv"/></b></p>      
                    
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
        <p style="text-align:center;"><strong><u>Део <xsl:value-of select="@Redni_broj"/></u></strong></p>
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
        <p style="text-align:center;"><b><xsl:value-of select="@Naziv"/></b></p>
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
        <p style="text-align:center;"><b><xsl:value-of select="@Naziv"/></b></p>
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
        <a name="Clan{@Broj_clana}">
            <p style="text-align:center;"><b>Члан <xsl:value-of select="@Broj_clana"/></b></p>
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
        </a>
    </xsl:template>
    
    <xsl:template match="ns3:Stav">
        <a name="Stav{@Id}"></a>
        <p style="text-align:center;">Став <xsl:value-of select="@Id"/></p>
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
        <a name="Alineja{@Id}"></a>
        <p>
            -<xsl:value-of select="."/>
        </p>
    </xsl:template>
    
    <xsl:template match="ns3:Reference">
        <div>
        <xsl:choose>
            <xsl:when test="@ref_tacka">
                <a href="http://localhost:8080/#/viewAkt/{$AktId}#Tacka{@ref_tacka}"> <xsl:value-of select="text()"/></a>
            </xsl:when>
            <xsl:when test="@ref_stav">
                <a href="http://localhost:8080/#/viewAkt/{$AktId}#Stav{@ref_stav}"> <xsl:value-of select="text()"/></a>
            </xsl:when>
            <xsl:when test="@ref_clan">
                <a href="http://localhost:8080/#/viewAkt/{$AktId}#Clan{@ref_clan}"> <xsl:value-of select="text()"/></a>
            </xsl:when>
            <xsl:when test="@ref_podtacka">
                <a href="http://localhost:8080/#/viewAkt/{$AktId}#Podtacka{@ref_podtacka}"> <xsl:value-of select="text()"/></a>
            </xsl:when>
            <xsl:when test="@ref_alineja">
                <a href="http://localhost:8080/#/viewAkt/{$AktId}#Alineja{@ref_alineja}"> <xsl:value-of select="text()"/></a>
            </xsl:when>
            <xsl:when test="@ref_akt">
                <a href="http://localhost:8080/#/viewAkt/{@ref_akt}"> <xsl:value-of select="text()"/></a>
            </xsl:when>
        </xsl:choose>
        </div>
    </xsl:template>
    
    <xsl:template match="ns3:Tacka">
        <a name="Tacka{@broj}"></a>
        <p style="text-align:center;">Тачка <xsl:value-of select="@broj"/></p>
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
        <a name="Podtacka{@broj}"></a>
        <p style="text-align:center;">Подтачка <xsl:value-of select="@broj"/></p>
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
