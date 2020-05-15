package isotopestudio.backdoor.core.utils;

import java.util.Random;

import doryanbessiere.isotopestudio.commons.GsonInstance;

public class Country {

	private String name;
	private String code;

	public Country(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static Country randomCountry() {
		String countries_json = "[ \r\n" + 
				"  {name: 'Afghanistan', code: 'AF'}, \r\n" + 
				"  {name: 'Åland Islands', code: 'AX'}, \r\n" + 
				"  {name: 'Albania', code: 'AL'}, \r\n" + 
				"  {name: 'Algeria', code: 'DZ'}, \r\n" + 
				"  {name: 'American Samoa', code: 'AS'}, \r\n" + 
				"  {name: 'AndorrA', code: 'AD'}, \r\n" + 
				"  {name: 'Angola', code: 'AO'}, \r\n" + 
				"  {name: 'Anguilla', code: 'AI'}, \r\n" + 
				"  {name: 'Antarctica', code: 'AQ'},  \r\n" + 
				"  {name: 'Argentina', code: 'AR'}, \r\n" + 
				"  {name: 'Armenia', code: 'AM'}, \r\n" + 
				"  {name: 'Aruba', code: 'AW'}, \r\n" + 
				"  {name: 'Australia', code: 'AU'}, \r\n" + 
				"  {name: 'Austria', code: 'AT'}, \r\n" + 
				"  {name: 'Azerbaijan', code: 'AZ'}, \r\n" + 
				"  {name: 'Bahamas', code: 'BS'}, \r\n" + 
				"  {name: 'Bahrain', code: 'BH'}, \r\n" + 
				"  {name: 'Bangladesh', code: 'BD'}, \r\n" + 
				"  {name: 'Barbados', code: 'BB'}, \r\n" + 
				"  {name: 'Belarus', code: 'BY'}, \r\n" + 
				"  {name: 'Belgium', code: 'BE'}, \r\n" + 
				"  {name: 'Belize', code: 'BZ'}, \r\n" + 
				"  {name: 'Benin', code: 'BJ'}, \r\n" + 
				"  {name: 'Bermuda', code: 'BM'}, \r\n" + 
				"  {name: 'Bhutan', code: 'BT'}, \r\n" + 
				"  {name: 'Bolivia', code: 'BO'}, \r\n" + 
				"  {name: 'Botswana', code: 'BW'},  \r\n" + 
				"  {name: 'Brazil', code: 'BR'}, \r\n" + 
				"  {name: 'Brunei Darussalam', code: 'BN'}, \r\n" + 
				"  {name: 'Bulgaria', code: 'BG'}, \r\n" + 
				"  {name: 'Burkina Faso', code: 'BF'}, \r\n" + 
				"  {name: 'Burundi', code: 'BI'}, \r\n" + 
				"  {name: 'Cambodia', code: 'KH'}, \r\n" + 
				"  {name: 'Cameroon', code: 'CM'}, \r\n" + 
				"  {name: 'Canada', code: 'CA'}, \r\n" + 
				"  {name: 'Chad', code: 'TD'}, \r\n" + 
				"  {name: 'Chile', code: 'CL'}, \r\n" + 
				"  {name: 'China', code: 'CN'}, \r\n" + 
				"  {name: 'Colombia', code: 'CO'}, \r\n" + 
				"  {name: 'Comoros', code: 'KM'}, \r\n" + 
				"  {name: 'Congo', code: 'CG'}, \r\n" + 
				"  {name: 'Cook Islands', code: 'CK'}, \r\n" + 
				"  {name: 'Costa Rica', code: 'CR'}, \r\n" + 
				"  {name: 'Cote D\\'Ivoire', code: 'CI'}, \r\n" + 
				"  {name: 'Croatia', code: 'HR'}, \r\n" + 
				"  {name: 'Cuba', code: 'CU'}, \r\n" + 
				"  {name: 'Cyprus', code: 'CY'}, \r\n" + 
				"  {name: 'Czech Republic', code: 'CZ'}, \r\n" + 
				"  {name: 'Denmark', code: 'DK'}, \r\n" + 
				"  {name: 'Djibouti', code: 'DJ'}, \r\n" + 
				"  {name: 'Dominica', code: 'DM'}, \r\n" + 
				"  {name: 'Ecuador', code: 'EC'}, \r\n" + 
				"  {name: 'Egypt', code: 'EG'}, \r\n" + 
				"  {name: 'El Salvador', code: 'SV'}, \r\n" + 
				"  {name: 'Equatorial Guinea', code: 'GQ'}, \r\n" + 
				"  {name: 'Eritrea', code: 'ER'}, \r\n" + 
				"  {name: 'Estonia', code: 'EE'}, \r\n" + 
				"  {name: 'Ethiopia', code: 'ET'},  \r\n" + 
				"  {name: 'Faroe Islands', code: 'FO'}, \r\n" + 
				"  {name: 'Fiji', code: 'FJ'}, \r\n" + 
				"  {name: 'Finland', code: 'FI'}, \r\n" + 
				"  {name: 'France', code: 'FR'}, \r\n" + 
				"  {name: 'French Guiana', code: 'GF'}, \r\n" + 
				"  {name: 'French Polynesia', code: 'PF'}, \r\n" + 
				"  {name: 'Gabon', code: 'GA'}, \r\n" + 
				"  {name: 'Gambia', code: 'GM'}, \r\n" + 
				"  {name: 'Georgia', code: 'GE'}, \r\n" + 
				"  {name: 'Germany', code: 'DE'}, \r\n" + 
				"  {name: 'Ghana', code: 'GH'}, \r\n" + 
				"  {name: 'Gibraltar', code: 'GI'}, \r\n" + 
				"  {name: 'Greece', code: 'GR'}, \r\n" + 
				"  {name: 'Greenland', code: 'GL'}, \r\n" + 
				"  {name: 'Grenada', code: 'GD'}, \r\n" + 
				"  {name: 'Guadeloupe', code: 'GP'}, \r\n" + 
				"  {name: 'Guam', code: 'GU'}, \r\n" + 
				"  {name: 'Guatemala', code: 'GT'}, \r\n" + 
				"  {name: 'Guernsey', code: 'GG'}, \r\n" + 
				"  {name: 'Guinea', code: 'GN'}, \r\n" + 
				"  {name: 'Guinea-Bissau', code: 'GW'}, \r\n" + 
				"  {name: 'Guyana', code: 'GY'}, \r\n" + 
				"  {name: 'Haiti', code: 'HT'}, \r\n" + 
				"  {name: 'Honduras', code: 'HN'}, \r\n" + 
				"  {name: 'Hong Kong', code: 'HK'}, \r\n" + 
				"  {name: 'Hungary', code: 'HU'}, \r\n" + 
				"  {name: 'Iceland', code: 'IS'}, \r\n" + 
				"  {name: 'India', code: 'IN'}, \r\n" + 
				"  {name: 'Indonesia', code: 'ID'}, \r\n" + 
				"  {name: 'Iraq', code: 'IQ'}, \r\n" + 
				"  {name: 'Ireland', code: 'IE'}, \r\n" + 
				"  {name: 'Isle of Man', code: 'IM'}, \r\n" + 
				"  {name: 'Israel', code: 'IL'}, \r\n" + 
				"  {name: 'Italy', code: 'IT'}, \r\n" + 
				"  {name: 'Jamaica', code: 'JM'}, \r\n" + 
				"  {name: 'Japan', code: 'JP'}, \r\n" + 
				"  {name: 'Jersey', code: 'JE'}, \r\n" + 
				"  {name: 'Jordan', code: 'JO'}, \r\n" + 
				"  {name: 'Kazakhstan', code: 'KZ'}, \r\n" + 
				"  {name: 'Kenya', code: 'KE'}, \r\n" + 
				"  {name: 'Kiribati', code: 'KI'}, \r\n" + 
				"  {name: 'Kuwait', code: 'KW'}, \r\n" + 
				"  {name: 'Kyrgyzstan', code: 'KG'}, \r\n" + 
				"  {name: 'Latvia', code: 'LV'}, \r\n" + 
				"  {name: 'Lebanon', code: 'LB'}, \r\n" + 
				"  {name: 'Lesotho', code: 'LS'}, \r\n" + 
				"  {name: 'Liberia', code: 'LR'}, \r\n" + 
				"  {name: 'Liechtenstein', code: 'LI'}, \r\n" + 
				"  {name: 'Lithuania', code: 'LT'}, \r\n" + 
				"  {name: 'Luxembourg', code: 'LU'}, \r\n" + 
				"  {name: 'Macao', code: 'MO'}, \r\n" + 
				"  {name: 'Madagascar', code: 'MG'}, \r\n" + 
				"  {name: 'Malawi', code: 'MW'}, \r\n" + 
				"  {name: 'Malaysia', code: 'MY'}, \r\n" + 
				"  {name: 'Maldives', code: 'MV'}, \r\n" + 
				"  {name: 'Mali', code: 'ML'}, \r\n" + 
				"  {name: 'Malta', code: 'MT'}, \r\n" + 
				"  {name: 'Marshall Islands', code: 'MH'}, \r\n" + 
				"  {name: 'Martinique', code: 'MQ'}, \r\n" + 
				"  {name: 'Mauritania', code: 'MR'}, \r\n" + 
				"  {name: 'Mauritius', code: 'MU'}, \r\n" + 
				"  {name: 'Mayotte', code: 'YT'}, \r\n" + 
				"  {name: 'Mexico', code: 'MX'}, \r\n" + 
				"  {name: 'Monaco', code: 'MC'}, \r\n" + 
				"  {name: 'Mongolia', code: 'MN'}, \r\n" + 
				"  {name: 'Montserrat', code: 'MS'}, \r\n" + 
				"  {name: 'Morocco', code: 'MA'}, \r\n" + 
				"  {name: 'Mozambique', code: 'MZ'}, \r\n" + 
				"  {name: 'Myanmar', code: 'MM'}, \r\n" + 
				"  {name: 'Namibia', code: 'NA'}, \r\n" + 
				"  {name: 'Nauru', code: 'NR'}, \r\n" + 
				"  {name: 'Nepal', code: 'NP'}, \r\n" + 
				"  {name: 'Netherlands', code: 'NL'}, \r\n" + 
				"  {name: 'New Caledonia', code: 'NC'}, \r\n" + 
				"  {name: 'New Zealand', code: 'NZ'}, \r\n" + 
				"  {name: 'Nicaragua', code: 'NI'}, \r\n" + 
				"  {name: 'Niger', code: 'NE'}, \r\n" + 
				"  {name: 'Nigeria', code: 'NG'}, \r\n" + 
				"  {name: 'Niue', code: 'NU'}, \r\n" + 
				"  {name: 'Norfolk Island', code: 'NF'}, \r\n" + 
				"  {name: 'Norway', code: 'NO'}, \r\n" + 
				"  {name: 'Oman', code: 'OM'}, \r\n" + 
				"  {name: 'Pakistan', code: 'PK'}, \r\n" + 
				"  {name: 'Palau', code: 'PW'},  \r\n" + 
				"  {name: 'Panama', code: 'PA'}, \r\n" + 
				"  {name: 'Paraguay', code: 'PY'}, \r\n" + 
				"  {name: 'Peru', code: 'PE'}, \r\n" + 
				"  {name: 'Philippines', code: 'PH'}, \r\n" + 
				"  {name: 'Pitcairn', code: 'PN'}, \r\n" + 
				"  {name: 'Poland', code: 'PL'}, \r\n" + 
				"  {name: 'Portugal', code: 'PT'}, \r\n" + 
				"  {name: 'Puerto Rico', code: 'PR'}, \r\n" + 
				"  {name: 'Qatar', code: 'QA'}, \r\n" + 
				"  {name: 'Reunion', code: 'RE'}, \r\n" + 
				"  {name: 'Romania', code: 'RO'}, \r\n" + 
				"  {name: 'RWANDA', code: 'RW'}, \r\n" + 
				"  {name: 'Saint Helena', code: 'SH'}, \r\n" + 
				"  {name: 'Saint Lucia', code: 'LC'}, \r\n" + 
				"  {name: 'Samoa', code: 'WS'}, \r\n" + 
				"  {name: 'San Marino', code: 'SM'}, \r\n" + 
				"  {name: 'Saudi Arabia', code: 'SA'}, \r\n" + 
				"  {name: 'Senegal', code: 'SN'}, \r\n" + 
				"  {name: 'Seychelles', code: 'SC'}, \r\n" + 
				"  {name: 'Sierra Leone', code: 'SL'}, \r\n" + 
				"  {name: 'Singapore', code: 'SG'}, \r\n" + 
				"  {name: 'Slovakia', code: 'SK'}, \r\n" + 
				"  {name: 'Slovenia', code: 'SI'}, \r\n" + 
				"  {name: 'Solomon Islands', code: 'SB'}, \r\n" + 
				"  {name: 'Somalia', code: 'SO'}, \r\n" + 
				"  {name: 'South Africa', code: 'ZA'}, \r\n" + 
				"  {name: 'Spain', code: 'ES'}, \r\n" + 
				"  {name: 'Sri Lanka', code: 'LK'}, \r\n" + 
				"  {name: 'Sudan', code: 'SD'}, \r\n" + 
				"  {name: 'Suriname', code: 'SR'}, \r\n" + 
				"  {name: 'Swaziland', code: 'SZ'}, \r\n" + 
				"  {name: 'Sweden', code: 'SE'}, \r\n" + 
				"  {name: 'Switzerland', code: 'CH'}, \r\n" + 
				"  {name: 'Tajikistan', code: 'TJ'}, \r\n" + 
				"  {name: 'Thailand', code: 'TH'}, \r\n" + 
				"  {name: 'Timor-Leste', code: 'TL'}, \r\n" + 
				"  {name: 'Togo', code: 'TG'}, \r\n" + 
				"  {name: 'Tokelau', code: 'TK'}, \r\n" + 
				"  {name: 'Tonga', code: 'TO'}, \r\n" + 
				"  {name: 'Tunisia', code: 'TN'}, \r\n" + 
				"  {name: 'Turkey', code: 'TR'}, \r\n" + 
				"  {name: 'Turkmenistan', code: 'TM'}, \r\n" + 
				"  {name: 'Tuvalu', code: 'TV'}, \r\n" + 
				"  {name: 'Uganda', code: 'UG'}, \r\n" + 
				"  {name: 'Ukraine', code: 'UA'}, \r\n" + 
				"  {name: 'United Kingdom', code: 'GB'}, \r\n" + 
				"  {name: 'United States', code: 'US'}, \r\n" + 
				"  {name: 'Uruguay', code: 'UY'}, \r\n" + 
				"  {name: 'Uzbekistan', code: 'UZ'}, \r\n" + 
				"  {name: 'Vanuatu', code: 'VU'}, \r\n" + 
				"  {name: 'Venezuela', code: 'VE'}, \r\n" + 
				"  {name: 'Viet Nam', code: 'VN'}, \r\n" + 
				"  {name: 'Western Sahara', code: 'EH'}, \r\n" + 
				"  {name: 'Yemen', code: 'YE'}, \r\n" + 
				"  {name: 'Zambia', code: 'ZM'}, \r\n" + 
				"  {name: 'Zimbabwe', code: 'ZW'} \r\n" + 
				"]";

		Country[] contries = GsonInstance.instance().fromJson(countries_json, Country[].class);
		return contries[new Random().nextInt(contries.length - 1)];
	}
}