del /q "D:\PrestaShop\Prestashop\allure-results"
FOR /D %%p IN ("D:\PrestaShop\Prestashop\allure-results") DO rmdir "%%p" /s /q
exit