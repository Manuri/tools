<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html"/>
	<!-- root match -->
	<xsl:template match="/report">
		<!-- perhaps we can put an image that is
                like the color lights (red/yellow/green)
                 depending on the status ?? -->
		<xsl:choose>
			<xsl:when test="not(error) and not(warning) and not(info)">
				<p class="error">
					<strong>The xml file you've submitted is not a valid services.xml!</strong>
				</p>
			</xsl:when>
			<xsl:otherwise>
				<xsl:choose>
					<xsl:when test="not(error) and not(warning)">
						<p class="success">
							<strong>There are no errors in your services.xml</strong>
							<!--<img src="/assets/images/green.gif" alt="green"/> -->
						</p>
					</xsl:when>
					<xsl:when test="not(error) and warning">
						<p class="warning">
							<strong>There are no errors in your services.xml but certain recommended items are missing.</strong>
							<!-- <img src="/assets/images/yellow.gif" alt="yellow"/> -->
						</p>
					</xsl:when>
					<xsl:otherwise>
						<p class="error">
							<strong>There are errors in your services.xml!</strong>
							<!-- <img src="/assets/images/red.gif" alt="red"/> -->
						</p>
					</xsl:otherwise>
				</xsl:choose>
				<xsl:if test="error">
					<p class="error"> We've found the following errors in your services.xml.</p>
					<ol>
						<xsl:for-each select="error">
							<li>
								<xsl:value-of select="."/>
							</li>
						</xsl:for-each>
					</ol>
				</xsl:if>
				<xsl:if test="warning">
					<p class="warning">There are some recommended items missing even though not required.</p>
					<ol>
						<xsl:for-each select="warning">
							<li>
								<xsl:value-of select="."/>
							</li>
						</xsl:for-each>
					</ol>
				</xsl:if>
				<xsl:if test="info">
					<p class="info">The following information was found during the verification process</p>
					<ol>
						<xsl:for-each select="info">
							<li>
								<xsl:value-of select="."/>
							</li>
						</xsl:for-each>
					</ol>
				</xsl:if>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>
