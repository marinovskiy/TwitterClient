package ua.marinovskiy.twitterclient.api;

public final class Constants {

    public static final String BASE_URL = "https://api.twitter.com/1.1/";

    public static final String TIME_LINE = "statuses/user_timeline.json?screen_name=twitterapi&count=20";

    public static final String TIME_LINE_HEADER_TITLE = "Authorization";

    public static final String TIME_LINE_HEADER_VALUE = "OAuth oauth_consumer_key=\"wM4pJzqAZUH9pubUQ9X0Tj5hv\", oauth_nonce=\"214a10ca4ac72f597675002120ff092e\", oauth_signature=\"BuVgCQZiz6TqJWYvhgNuDhrFsDM%3D\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"1455191404\", oauth_token=\"4860052989-J1IXOquzenWA9IJCahL6h1W9ejRSCnS7pJKQR3m\", oauth_version=\"1.0\"";

}
