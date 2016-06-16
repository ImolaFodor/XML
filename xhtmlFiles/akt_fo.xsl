<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:b="http://www.ftn.uns.ac.rs/xpath/examples" 
    xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
    
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="akt-page">
                    <fo:region-body margin="1in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            
            <fo:page-sequence master-reference="akt-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="sans-serif" font-size="32px" font-weight="bold" padding="30px">
                        Bookstore (XSL-FO)
                    </fo:block>
                    <fo:block text-indent="30px">
                        Total number of books in the bookstore: 
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="count(b:bookstore/b:book)"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="28px" font-weight="bold" padding="30px">
                        Available books:
                    </fo:block>
                    <fo:block text-indent="30px">
                        Highlighting (*) the book titles with a  
                        <fo:inline font-weight="bold">price less than $40</fo:inline>.
                    </fo:block>
                    
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
