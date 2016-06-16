<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
    xmlns:ns3="aktovi" xmlns:am="amandmani" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xlink="http://www.w3.org/1999/xlink"
    >
    <xsl:template match="/">
        
        <html>
            <head>
                <meta charset="UTF-8"/>
					<xsl:value-of select="am:Amandman/am:Kontekst"/>
					<h style="text-align:center;"><b><xsl:value-of select="am:Amandman/am:Operacija"/></b></h>      
                    
            </head>
            
            <body style="font-family: Calibri">
            	<xsl:for-each select="am:Amandman/am:Sadrzaj">
            	    <p>
					<xsl:value-of select="ns3:Glavni_deo"/>
					</p>
				</xsl:for-each>
					
		 	</body>
            
        </html>
    </xsl:template>
    
  </xsl:stylesheet>