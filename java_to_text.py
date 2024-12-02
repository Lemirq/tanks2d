import os

def extract_java_code(folder_path, output_file):
    try:
        # Open the output file in write mode
        with open(output_file, 'w') as output:
            # Iterate over all files in the given folder
            for file_name in os.listdir(folder_path):
                # Check if the file has a .java extension
                if file_name.endswith('.java'):
                    file_path = os.path.join(folder_path, file_name)
                    
                    # Write separator, file name, and another separator
                    output.write("-" * 50 + "\n")
                    output.write(f"File: {file_name}\n")
                    output.write("-" * 50 + "\n")

                    # Read and write the content of the .java file
                    with open(file_path, 'r') as java_file:
                        output.write(java_file.read())
                        output.write("\n\n")

        print(f"Code from Java files has been written to {output_file}.")
    except Exception as e:
        print(f"An error occurred: {e}")

# Specify the folder containing Java files and the output text file
folder_path = "/Users/vs/Coding/tanks2d/src/main/java/com/tanks2d"
output_file = "assignnment.txt"

# Call the function
extract_java_code(folder_path, output_file)
