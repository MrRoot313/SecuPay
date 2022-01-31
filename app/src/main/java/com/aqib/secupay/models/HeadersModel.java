package com.aqib.secupay.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HeadersModel {

    @SerializedName("Accept")
    @Expose
    private String accept;
    @SerializedName("Accept-Encoding")
    @Expose
    private String acceptEncoding;
    @SerializedName("Accept-Language")
    @Expose
    private String acceptLanguage;
    @SerializedName("Cookie")
    @Expose
    private String cookie;
    @SerializedName("Dnt")
    @Expose
    private String dnt;
    @SerializedName("Host")
    @Expose
    private String host;
    @SerializedName("Sec-Ch-Ua")
    @Expose
    private String secChUa;
    @SerializedName("Sec-Ch-Ua-Mobile")
    @Expose
    private String secChUaMobile;
    @SerializedName("Sec-Ch-Ua-Platform")
    @Expose
    private String secChUaPlatform;
    @SerializedName("Sec-Fetch-Dest")
    @Expose
    private String secFetchDest;
    @SerializedName("Sec-Fetch-Mode")
    @Expose
    private String secFetchMode;
    @SerializedName("Sec-Fetch-Site")
    @Expose
    private String secFetchSite;
    @SerializedName("Sec-Fetch-User")
    @Expose
    private String secFetchUser;
    @SerializedName("Upgrade-Insecure-Requests")
    @Expose
    private String upgradeInsecureRequests;
    @SerializedName("User-Agent")
    @Expose
    private String userAgent;
    @SerializedName("X-Amzn-Trace-Id")
    @Expose
    private String xAmznTraceId;

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getAcceptEncoding() {
        return acceptEncoding;
    }

    public void setAcceptEncoding(String acceptEncoding) {
        this.acceptEncoding = acceptEncoding;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public void setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getDnt() {
        return dnt;
    }

    public void setDnt(String dnt) {
        this.dnt = dnt;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSecChUa() {
        return secChUa;
    }

    public void setSecChUa(String secChUa) {
        this.secChUa = secChUa;
    }

    public String getSecChUaMobile() {
        return secChUaMobile;
    }

    public void setSecChUaMobile(String secChUaMobile) {
        this.secChUaMobile = secChUaMobile;
    }

    public String getSecChUaPlatform() {
        return secChUaPlatform;
    }

    public void setSecChUaPlatform(String secChUaPlatform) {
        this.secChUaPlatform = secChUaPlatform;
    }

    public String getSecFetchDest() {
        return secFetchDest;
    }

    public void setSecFetchDest(String secFetchDest) {
        this.secFetchDest = secFetchDest;
    }

    public String getSecFetchMode() {
        return secFetchMode;
    }

    public void setSecFetchMode(String secFetchMode) {
        this.secFetchMode = secFetchMode;
    }

    public String getSecFetchSite() {
        return secFetchSite;
    }

    public void setSecFetchSite(String secFetchSite) {
        this.secFetchSite = secFetchSite;
    }

    public String getSecFetchUser() {
        return secFetchUser;
    }

    public void setSecFetchUser(String secFetchUser) {
        this.secFetchUser = secFetchUser;
    }

    public String getUpgradeInsecureRequests() {
        return upgradeInsecureRequests;
    }

    public void setUpgradeInsecureRequests(String upgradeInsecureRequests) {
        this.upgradeInsecureRequests = upgradeInsecureRequests;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getXAmznTraceId() {
        return xAmznTraceId;
    }

    public void setXAmznTraceId(String xAmznTraceId) {
        this.xAmznTraceId = xAmznTraceId;
    }

}
