import datetime
import zipfile


def bruteforce_dates(start_year, end_year):
    # Define the format for DDMMYYYY
    date_format = "%d%m%Y"

    # List to store valid dates
    valid_dates = []

    # Loop through years
    for year in range(start_year, end_year + 1):
        # Loop through months
        for month in range(1, 13):
            # Loop through days
            for day in range(1, 32):
                try:
                    # Attempt to create a date object
                    date_str = f"{day:02d}{month:02d}{year}"
                    date_obj = datetime.datetime.strptime(date_str, date_format)
                    valid_dates.append(date_str)
                except ValueError:
                    # Ignore invalid date combinations (e.g., Feb 30)
                    pass

    return valid_dates

start_year = 1940
end_year = 2000

valid_dates = bruteforce_dates(start_year, end_year)
# print("Found valid dates:", valid_dates)



def extract_zip_with_password(zip_path, extract_path, password):
    try:
        with zipfile.ZipFile(zip_path, "r") as zip_ref:
            # Set the password to extract the contents
            zip_ref.setpassword(password.encode("utf-8"))

            # Extract all contents to the specified path
            zip_ref.extractall(extract_path)
            print(f"Successfully extracted contents from {zip_path} to {extract_path}")
            print("Password:", password)
            
    except zipfile.BadZipFile:
        pass
    except RuntimeError as e:
        pass


# Example usage
zip_file_path = "C:\\Users\\loren\\Desktop\\Cyberexercise"
extract_folder_path = "C:\\Users\\loren\\Desktop\\Cyberexercise"
p = valid_dates[0]

for p in valid_dates:
    # Append content to the file
    extract_zip_with_password(zip_file_path, extract_folder_path, p)
