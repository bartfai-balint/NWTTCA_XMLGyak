<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">

            <vizsga>
                <xsl:for-each select="Cars/Car">
                    <vehicle>
                        <vehicleColor>
                            <xsl:value-of select="Color"/>
                        </vehicleColor>
                        <vehicleYear>
                            <xsl:value-of select="Year"/>
                        </vehicleYear>
                    </vehicle>
                </xsl:for-each>
            </vizsga>

            <TotalQuantity>
                    <xsl:value-of select="sum()"/> ///// Sum of quantity of all car (<Quantity>)
            </TotalQuantity>
    </xsl:template>

</xsl:stylesheet>